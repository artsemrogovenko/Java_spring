package com.example.webclient.contorllers;

import com.example.webclient.aspect.LogMethod;
import com.example.webclient.models.Task;
import com.example.webclient.models.UserId;
import com.example.webclient.serivices.WebService;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.net.ConnectException;
import java.security.Principal;

import static feign.FeignException.*;

/**
 * Контроллер магазина.
 */

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class WebController {
    /**
     * Сервис магазина.
     */
    private final UserId userId = new UserId(3L); // TODO id пользователя
    private final WebService webService;

    /**
     * Домашняя страница.
     */
    @LogMethod
    @GetMapping
    public String homePage(Model model,
                           @RequestParam(value = "confirm", required = false) String confirm) {
        model.addAttribute("tasks", webService.getAll());
        if (confirm != null) {
            model.addAttribute("confirm", confirm);
        }
        boolean haslist=webService.getAll().isEmpty();
        model.addAttribute("listempty", haslist);

        model.addAttribute("userid", userId.getValue());
//        System.out.println("id = " + userId.getValue());
        return "home";

    }

    @LogMethod
    @GetMapping("/admin-only")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAll(Model model) {//показать только доступные мне задачи
        model.addAttribute("tasks", webService.showAll());
        return "admin";
    }

    /**
     * для создания новой задачи
     */
    @LogMethod
    @PostMapping("/create")
    public String createTask(@RequestParam("description") String description,
                             RedirectAttributes redirectAttributes, Model model,
                             HttpServletRequest request) {
        Task newTask = new Task();
        newTask.setDescription(description);
//        System.out.println(newTask);
        webService.createNew(newTask);
//        System.out.println(redirectAttributes);
        redirectAttributes.addAttribute("confirm", "задача создана");
        model.addAttribute("tasks", webService.showAll());
        return "admin";
    }

    @PostMapping("/lazyGenerate")
    public String lazyGenerate() {
        webService.lazyFill();
        return "redirect:/";
    }

    /**
     * Взять задачу .
     */
    @LogMethod
    @PostMapping("/take/{id}")
    public String takeMe(@PathVariable("id") Long id, @RequestParam("userid") String userid,
                         RedirectAttributes redirectAttributes) {

        Task task = webService.getOne(id).getBody();
        //   System.out.println(webService.getOne(id).getBody());
        webService.assign(task.getId(), Long.parseLong(userid), task);
        redirectAttributes.addAttribute("confirm", "задание принято");
        return "redirect:/";
//        return "home";
    }

    @LogMethod
    @PostMapping("/{id}/rollback")
    public String cancel(@PathVariable("id") Long id, @RequestParam("userid") String userid,
                         RedirectAttributes redirectAttributes) {
        Task product = webService.getOne(id).getBody();
        webService.rollbackTake(product, Long.parseLong(userid));
        redirectAttributes.addAttribute("confirm", "одной задачей меньше");
        return "redirect:/";
//        return "home";
    }

    @LogMethod
    @PostMapping("/{id}/complete")
    public String finishTask(@PathVariable("id") Long id, @RequestParam("userid") String userid,
                             RedirectAttributes redirectAttributes) {
        webService.completeTask(id, Long.parseLong(userid)); //
        redirectAttributes.addAttribute("confirm", "Поздравляю!");
        return "redirect:/";
    }

    /**
     * Страница с ошибками
     */
    @LogMethod
    @ExceptionHandler(value = RuntimeException.class)
    public String errorPage(Principal principal, RuntimeException e, Model model,
                            HttpServletRequest request) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("tasks", webService.getAll());
        return "home";
    }

    @LogMethod
    @ExceptionHandler(value = IllegalStateException.class)
    public String infoDublicate(Principal principal, RuntimeException e, Model model,
                                HttpServletRequest request) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("tasks", webService.getAll());
        return "redirect:" + request.getRequestURI();
    }

    @LogMethod
    @ExceptionHandler(value = FeignException.InternalServerError.class)
    public String errorPage(Principal principal, FeignException e, Model model,
                            HttpServletRequest request) {
        model.addAttribute("message", e.getMessage());
        return "redirect:/";
    }

    @LogMethod
    @ExceptionHandler(value = FeignException.NotFound.class)
    public String errorStorage(Principal principal, NotFound n, Model model,
                               HttpServletRequest request) {
        model.addAttribute("message", "сервер задач недоступен" + n.getMessage());
        //model.addAttribute("tasks", webService.getAll());
        return "redirect:/";
    }


    @LogMethod
    @ExceptionHandler(value = ConnectException.class)
    public String errorConnection(Principal principal, ConnectException e, Model model,
                                  HttpServletRequest request) {
        model.addAttribute("message", e.getMessage());
        return "redirect:/";
    }

}

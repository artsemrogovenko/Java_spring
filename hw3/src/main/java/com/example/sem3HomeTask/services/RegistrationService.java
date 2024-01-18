package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    // Внедрение зависимости через конструктор
    public RegistrationService(DataProcessingService dataProcessingService, NotificationService notificationService, UserService userService) {
        this.dataProcessingService = dataProcessingService;
        this.notificationService = notificationService;
        this.userService = userService;
    }

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    //@Autowired
    private DataProcessingService dataProcessingService;
    private NotificationService notificationService;
    private UserService userService;


    /**
     * Разработать метод processRegistration в котором:
     * - создается пользователь из параметров метода
     * - созданный пользователь добавляется в репозиторий
     * - через notificationService выводится сообщение в консоль
     */
    // notificationService встроен в метод createuser
    public void processRegistration(User usr) {
        dataProcessingService.addUserToList(userService.createUser(usr.getName(), usr.getAge(), usr.getEmail()));
    }
}

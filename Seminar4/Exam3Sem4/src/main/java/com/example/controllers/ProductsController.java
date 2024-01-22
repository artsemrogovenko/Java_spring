package com.example.controllers;

import com.example.model.Product;
import com.example.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductsController {

    private final ProductService productService;
/** lombok сам сделал конструктор*/
//    public ProductsController(ProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "products.html";
    }

    @PostMapping("/products")
    public String addProduct(Product p, Model model) {
        productService.addProduct(p);

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "products.html";
    }
}

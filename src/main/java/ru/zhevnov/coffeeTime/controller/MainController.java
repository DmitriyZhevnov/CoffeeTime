package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.zhevnov.coffeeTime.service.IProductService;

@Controller
@SessionAttributes("person")
@RequestMapping("/main")
public class MainController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public String showMainPage() {
        return "main/mainPage";
    }

    @GetMapping("/newOrder")
    public String newOrder(Model model) {
        model.addAttribute("listOfProducts", productService.returnAllProducts());
        return "main/newOrder";
    }
}

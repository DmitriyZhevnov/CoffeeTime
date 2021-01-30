package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.zhevnov.coffeeTime.entity.Product;
import ru.zhevnov.coffeeTime.service.ICategoryService;
import ru.zhevnov.coffeeTime.service.IProductService;

import java.util.List;

@Controller
@SessionAttributes("person")
@RequestMapping("/main")
public class MainController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String showMainPage() {
        return "main/mainPage";
    }

    @GetMapping("/newOrder")
    public String newOrder(Model model) {
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        return "main/newOrder";
    }
}

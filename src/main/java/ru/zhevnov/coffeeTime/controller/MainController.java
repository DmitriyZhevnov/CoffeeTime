package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.service.*;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/main")
public class MainController {
    @Autowired
    private IShiftService shiftService;

    @GetMapping
    public String showMainPage() {
        return "main/mainPage";
    }

    @GetMapping("/closeShift")
    public String closeShift(@ModelAttribute("user") Employee employee, Model model) {
        shiftService.closeShift(employee.getId());
        return "redirect:/login";
    }
}

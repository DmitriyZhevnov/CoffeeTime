package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.service.IEmployeeService;

@Controller
@RequestMapping("/login")
@SessionAttributes("person")
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @ModelAttribute("person")
    public Employee newEmployee() {
        return new Employee();
    }

    @GetMapping
    public String showLoginPage() {
        return "login/loginPage";
    }

    @PostMapping
    public String login(@RequestParam("login") String login, @RequestParam("password") String pas, Model model) {
        Employee employee = employeeService.checkAndReturnEmployeeByLoginAndPassword(login, pas);
        if (employee == null) {
            return "страница не существующего";
        }
        model.addAttribute("person", employee);
        return "redirect:/main";
    }
}

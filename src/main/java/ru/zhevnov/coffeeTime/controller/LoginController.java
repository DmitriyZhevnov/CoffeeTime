package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhevnov.coffeeTime.dao.ICommercialObjectDao;
import ru.zhevnov.coffeeTime.dao.IShiftDao;
import ru.zhevnov.coffeeTime.entity.CommercialObject;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.service.IEmployeeService;

@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    ////Изменить! Сделать сервис!!
    @Autowired
    private IShiftDao shiftDao;
    @Autowired
    private ICommercialObjectDao commercialObjectDao;
    ///

    @ModelAttribute("user")
    public Employee newEmployee() {
        return new Employee();
    }

    @GetMapping
    public String showLoginPage(Model model) {
        model.addAttribute("commercialObjects", commercialObjectDao.returnListOdCommercialObjects());
        return "login/loginPage";
    }

    @PostMapping
    public String login(@RequestParam("comObj") int objectId, @RequestParam("login") String login, @RequestParam("password") String pas, Model model) {
        Employee employee = employeeService.checkAndReturnEmployeeByLoginAndPassword(login, pas);
        if (employee == null) {
            return "страница не существующего";
        }
        shiftDao.checkOrOpenTheShift(employee, objectId);
        model.addAttribute("user", employee);
        return "redirect:/main";
    }
}

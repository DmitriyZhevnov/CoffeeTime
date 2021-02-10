package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.zhevnov.coffeeTime.dao.IShiftDao;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.service.IShiftService;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private /*IShiftService*/ IShiftDao shiftService;

    @GetMapping()
    public String newOrder(@ModelAttribute("user") Employee employee, Model model) {
        model.addAttribute("report", shiftService.makeReport(employee.getId()));
        return "main/report/report";
    }
}

package ru.zhevnov.coffeeTime.controller;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.service.*;

import javax.servlet.http.HttpSession;

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
    public String closeShift(@ModelAttribute("user") Employee employee, HttpSession httpsession, SessionStatus status) {
        shiftService.closeShift(employee.getId());
        status.setComplete();
        httpsession.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/block")
    public String block(HttpSession httpsession, SessionStatus status) {
        status.setComplete();
        httpsession.invalidate();
        return "redirect:/login";
    }
}

package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhevnov.coffeeTime.dao.IShiftDao;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Shift;
import ru.zhevnov.coffeeTime.service.ICommercialObjectService;
import ru.zhevnov.coffeeTime.service.IShiftService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private /*IShiftService*/ IShiftDao shiftService;
    @Autowired
    private ICommercialObjectService commercialObjectService;

    @GetMapping()
    public String newOrder(@ModelAttribute("user") Employee employee, Model model) {
        Date date = new Date(System.currentTimeMillis());
        Shift shift = shiftService.returnOpenedShiftByEmployeeId(employee.getId());
        model.addAttribute("fromDate", date);
        model.addAttribute("toDate", date);
        model.addAttribute("report", shiftService.makeReport(shift.getCommercialObject().getId(), date, date));
        model.addAttribute("allCommercialObjects", commercialObjectService.returnAllCommercialObjects());
        return "main/report/report";
    }

    @PostMapping("/date")
    public String newOrder(@ModelAttribute("user") Employee employee, Model model,
                           @RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate,
                           @RequestParam("idCommercialObject") int idCommercialObject) {
       model.addAttribute("fromDate", fromDate);
       model.addAttribute("toDate", toDate);
       model.addAttribute("report", shiftService.makeReport(idCommercialObject, fromDate, toDate));
        model.addAttribute("allCommercialObjects", commercialObjectService.returnAllCommercialObjects());
        return "main/report/report";
    }
}

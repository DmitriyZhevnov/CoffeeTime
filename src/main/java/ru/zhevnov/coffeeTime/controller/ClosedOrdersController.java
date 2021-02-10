package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhevnov.coffeeTime.dao.IOrderDao;
import ru.zhevnov.coffeeTime.dao.OrderDao;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.service.OrderService;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/closedOrders")
public class ClosedOrdersController {

    @Autowired
    private /*IOrderService*/ IOrderDao orderService;

    @GetMapping()
    public String newOrder(@ModelAttribute("user") Employee employee, Model model) {
//        model.addAttribute("orderItems", orderService.returnAllOrderItemsByEmployeeId(employee.getId()));
        model.addAttribute("orders", orderService.returnAllOrdersByEmployeeId(employee.getId()));
        return "main/orders/closedOrders";
    }

    @GetMapping("/{idOrder}")
    public String showOrderInfo(@PathVariable(name = "idOrder") int idOrder,@ModelAttribute("user") Employee employee , Model model){
        model.addAttribute("order", orderService.returnOrderById(idOrder));
        return "main/orders/ordersInfo";
    }
}

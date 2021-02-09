package ru.zhevnov.coffeeTime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/closedOrders")
public class ClosedOrdersController {
    @GetMapping()
    public String newOrder() {
        return "main/orders/closedOrders";
    }
}

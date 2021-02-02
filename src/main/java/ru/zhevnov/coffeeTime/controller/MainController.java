package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.service.IBasketService;
import ru.zhevnov.coffeeTime.service.ICategoryService;
import ru.zhevnov.coffeeTime.service.IClientService;
import ru.zhevnov.coffeeTime.service.IShiftService;

@Controller
@SessionAttributes({"user","phoneNumber"})
@RequestMapping("/main")
public class MainController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBasketService basketService;
    @Autowired
    private IShiftService shiftService;
    @Autowired
    private IClientService clientService;

    @GetMapping
    public String showMainPage() {
        return "main/mainPage";
    }

    @GetMapping("/newOrder")
    public String newOrder(@RequestParam(value = "phoneNumber", required = false) String phoneNumber, Model model, @ModelAttribute("user") Employee employee) {
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee, phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }

    @GetMapping("/newOrder/add/{idProduct}")
    public String addProduct(@RequestParam(value = "phoneNumber", required = false) String phoneNumber, @PathVariable(name = "idProduct") int idProduct, Model model, @ModelAttribute("user") Employee employee) {
        basketService.addProductToBasket(employee.getId(), idProduct, 1);
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee, phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }

    @GetMapping("/newOrder/sub/{idProduct}")
    public String subProduct(@RequestParam(value = "phoneNumber", required = false) String phoneNumber,@PathVariable(name = "idProduct") int idProduct, Model model, @ModelAttribute("user") Employee employee) {
        basketService.addProductToBasket(employee.getId(), idProduct, -1);
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee,phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }

    @GetMapping("/newOrder/delete/{idProduct}")
    public String deleteProduct(@RequestParam(value = "phoneNumber", required = false) String phoneNumber,@PathVariable(name = "idProduct") int idProduct, Model model, @ModelAttribute("user") Employee employee) {
        basketService.deleteItem(employee, idProduct);
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee, phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }

    @PostMapping("/newOrder/makeDiscount")
    public String makeDiscount(@RequestParam(value = "phoneNumber", required = false) String phoneNumber, Model model, @ModelAttribute("user") Employee employee) {
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee, phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }

    @PostMapping("newClient")
    public String registerNewClient(Model model, @RequestParam(value = "pNumber") String phoneNumber, @RequestParam("name") String name, @ModelAttribute("user") Employee employee){
        clientService.registerNewClient(name, phoneNumber);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee, phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }

    @GetMapping("/closeShift")
    public String closeShift(@ModelAttribute("user") Employee employee, Model model) {
        shiftService.closeShift(employee);
        return "redirect:/login";
    }
}

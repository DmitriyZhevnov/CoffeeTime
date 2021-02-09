package ru.zhevnov.coffeeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.service.*;

@Controller
@SessionAttributes({"user", "phoneNumber", "coffees", "drinks", "additions", "bars"})
@RequestMapping("/newOrder")
public class NewOrderController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBasketService basketService;
    @Autowired
    private IShiftService shiftService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICommercialObjectService commercialObjectService;

    @GetMapping()
    public String newOrder(@RequestParam(value = "phoneNumber", required = false) String phoneNumber, Model model, @ModelAttribute("user") Employee employee) {
        model.addAttribute("phoneNumber", "");
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("drinks", categoryService.returnAllDrinks());
        model.addAttribute("additions", categoryService.returnAllAdditions());
        model.addAttribute("bars", categoryService.returnAllBars());
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee.getId(), phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee.getId()));
        return "main/newOrder/newOrder";
    }

    @GetMapping("/add/{idProduct}")
    public String addProduct(@RequestParam(value = "phoneNumber", required = false) String phoneNumber, @PathVariable(name = "idProduct") int idProduct, Model model, @ModelAttribute("user") Employee employee) {
        basketService.addProductToBasket(employee.getId(), idProduct);
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee.getId(), phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee.getId()));
        return "main/newOrder/newOrder";
    }

    @GetMapping("/sub/{idProduct}")
    public String subProduct(@RequestParam(value = "phoneNumber", required = false) String phoneNumber, @PathVariable(name = "idProduct") int idProduct, Model model, @ModelAttribute("user") Employee employee) {
        basketService.submitProductInBasket(employee.getId(), idProduct);
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee.getId(), phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee.getId()));
        return "main/newOrder/newOrder";
    }

    @GetMapping("/delete/{idProduct}")
    public String deleteProduct(@RequestParam(value = "phoneNumber", required = false) String phoneNumber, @PathVariable(name = "idProduct") int idProduct, Model model, @ModelAttribute("user") Employee employee) {
        basketService.deleteItem(employee.getId(), idProduct);
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee.getId(), phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee.getId()));
        return "main/newOrder/newOrder";
    }

    @PostMapping("/makeDiscount")
    public String makeDiscount(@RequestParam(value = "phoneNumber", required = false) String phoneNumber, Model model, @ModelAttribute("user") Employee employee) {
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee.getId(), phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee.getId()));
        return "main/newOrder/newOrder";
    }

    @PostMapping("/newClient")
    public String registerNewClient(Model model, @RequestParam(value = "pNumber") String phoneNumber, @RequestParam("name") String name, @ModelAttribute("user") Employee employee) {
        clientService.registerNewClient(name, phoneNumber);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee.getId(), phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee.getId()));
        return "main/newOrder/newOrder";
    }

    @PostMapping("/pay")
    public String payAndMakeOrder(@RequestParam("paymentType") String paymentType, @ModelAttribute("user") Employee employee,
                                  @ModelAttribute("phoneNumber") String phoneNumber,
                                  @RequestParam(value = "cashAmount", required = false) String cash,
                                  @RequestParam(value = "cardAmount", required = false) String card, Model model) {
        System.out.println(card);
        System.out.println(cash);
        commercialObjectService.submitItemsFromCommercialObjectsStorage(employee.getId());
        orderService.saveNewOrder(employee.getId(), phoneNumber, paymentType, card, cash);
        model.addAttribute("phoneNumber", "");
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("totalCost", basketService.returnTotalCostOfTheOrder(employee.getId(), phoneNumber));
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee.getId()));
        return "main/newOrder/newOrder";
    }

}

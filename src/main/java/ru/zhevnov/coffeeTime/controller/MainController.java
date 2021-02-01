package ru.zhevnov.coffeeTime.controller;

import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.zhevnov.coffeeTime.dao.IShiftDao;
import ru.zhevnov.coffeeTime.entity.BasketItem;
import ru.zhevnov.coffeeTime.entity.Employee;
import ru.zhevnov.coffeeTime.entity.Product;
import ru.zhevnov.coffeeTime.service.IBasketService;
import ru.zhevnov.coffeeTime.service.ICategoryService;
import ru.zhevnov.coffeeTime.service.IProductService;

import java.util.List;

@Controller
@SessionAttributes("user")
@RequestMapping("/main")
public class MainController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBasketService basketService;
    //////Изменить на сервис
    @Autowired
    private IShiftDao shiftDao;
    //////////////////

    @GetMapping
    public String showMainPage() {
        return "main/mainPage";
    }

    @GetMapping("/newOrder")
    public String newOrder(Model model, @ModelAttribute("user") Employee employee) {
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }

    @GetMapping("/newOrder/add/{idProduct}")
    public String addProduct(@PathVariable(name = "idProduct") int idProduct, Model model, @ModelAttribute("user") Employee employee) {
        basketService.addProductToBasket(employee.getId(), idProduct,1);
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }
    @GetMapping("/newOrder/sub/{idProduct}")
    public String subProduct(@PathVariable(name = "idProduct") int idProduct, Model model, @ModelAttribute("user") Employee employee) {
        basketService.addProductToBasket(employee.getId(), idProduct,-1);
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }
    @GetMapping("/newOrder/delete/{idProduct}")
    public String deleteProduct(@PathVariable(name = "idProduct") int idProduct, Model model, @ModelAttribute("user") Employee employee) {
        basketService.deleteItem(employee, idProduct);
        model.addAttribute("coffees", categoryService.returnAllCoffees());
        model.addAttribute("basket", basketService.returnListOfProductsInBasket(employee));
        return "main/newOrder";
    }

    @GetMapping("/closeShift")
    public String closeShift(@ModelAttribute("user") Employee employee, Model model){
        shiftDao.closeShift(employee);
        return "redirect:/login";
    }
}

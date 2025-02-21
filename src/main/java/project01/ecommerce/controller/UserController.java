package project01.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project01.ecommerce.model.User;
import project01.ecommerce.service.CartService;
import project01.ecommerce.service.ProductService;
import project01.ecommerce.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    public UserController(UserService userService, ProductService productService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping()
    public String getHomeGuest() {
        return "guest";
    }

    @GetMapping(value = "/login")
    public String getLogin() {
        return "auth/login";
    }

    @GetMapping(value = "/signup")
    public String getRegisterForm(Model model, User user) {
        model.addAttribute("user", user);
        return "auth/signup";
    }

    @PostMapping(value = "/signup")
    public String postRegisterForm(@ModelAttribute("user") User user, Model model) {
        if (userService.existUserByUsername(user.getUsername())) {
            model.addAttribute("messageUserExist", "Username is taken");
            return "auth/signup";
        }
        userService.save(user);
        model.addAttribute("message", "Registration Successfully !");
        return "auth/signup";
    }

    @GetMapping(value = "/home")
    public String homePage(){
        return "home";
    }
    @GetMapping(value = "/sneaker")
    public String sneakersPage(Model model){
        model.addAttribute("totalProduct", productService.countTotalProducts());
        model.addAttribute("products", productService.getListOfProducts());
        return "sneakers";
    }
    @PostMapping(value = "/sneaker/add")
    public String addToCart(Model model,
                            @RequestParam("productId") Long productId,
                            @RequestParam("quantity") int quantity) {
        cartService.addToCart(productId, quantity);
        model.addAttribute("messageAddToCart", "Added to cart!");
        return "redirect:/sneaker";
    }
}

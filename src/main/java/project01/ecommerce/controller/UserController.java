package project01.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project01.ecommerce.model.User;
import project01.ecommerce.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public String sneakersPage(){
        return "sneakers";
    }
}

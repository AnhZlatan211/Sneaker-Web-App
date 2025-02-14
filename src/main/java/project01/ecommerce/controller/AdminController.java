package project01.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project01.ecommerce.model.User;
import project01.ecommerce.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        model.addAttribute("users", userService.getListOfUsers());
        model.addAttribute("totalUsers", userService.countTotalUsers());
        return "admin/admin";
    }

    @GetMapping(value = "/product")
    public String getAdminProductPage(Model model) {
        model.addAttribute("users", userService.getListOfUsers());
        model.addAttribute("totalUsers", userService.countTotalUsers());
        return "admin/adminProduct";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("messageDeleteUser", "Deleted user successfully!");
        return "redirect:/admin";
    }

    @PostMapping(value = "/search/user")
    public String searchUserByName(@RequestParam("keyword") String keyword, Model model) {
        List<User> users = userService.findByNameContaining(keyword);
        model.addAttribute("users", users);
        model.addAttribute("totalUsers", userService.countTotalUsers());
        return "admin/admin";
    }
}

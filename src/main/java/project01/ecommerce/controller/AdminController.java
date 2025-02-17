package project01.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project01.ecommerce.model.Product;
import project01.ecommerce.model.User;
import project01.ecommerce.service.ProductService;
import project01.ecommerce.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ProductService productService;

    public AdminController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        model.addAttribute("totalUsers", userService.countTotalUsers());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        return "admin/admin";
    }

    @GetMapping(value = "/user")
    public String getAdminUserPage(Model model) {
        model.addAttribute("users", userService.getListOfUsers());
        model.addAttribute("totalUsers", userService.countTotalUsers());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        return "admin/adminUser";
    }
    @GetMapping(value = "/user/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("messageDeleteUser", "Deleted user successfully!");
        return "redirect:/admin/user";
    }
    @PostMapping(value = "/search/user")
    public String searchUserByName(@RequestParam("keyword") String keyword, Model model) {
        List<User> users = userService.findByNameContaining(keyword);
        model.addAttribute("users", users);
        return "admin/adminUser";
    }

    @GetMapping(value = "/product")
    public String getAdminProductPage(Model model) {
        model.addAttribute("products", productService.getListOfProducts());
        model.addAttribute("totalUsers", userService.countTotalUsers());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        return "admin/adminProduct";
    }
    @GetMapping(value = "/product/create")
    public String getCreateProduct(Model model, Product product) {
        model.addAttribute("product", product);
        return "admin/adminProductCreate";
    }
    @GetMapping(value = "/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productService.delete(id);
        model.addAttribute("messageDeleteProduct", "Deleted product successfully!");
        return "redirect:/admin/product";
    }
}

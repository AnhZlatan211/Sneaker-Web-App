package project01.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project01.ecommerce.model.Product;
import project01.ecommerce.service.OrderService;
import project01.ecommerce.service.ProductService;
import project01.ecommerce.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    public AdminController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        model.addAttribute("totalUsers", userService.countTotalUsers());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        model.addAttribute("totalOrders", orderService.countTotalOrders());
        return "admin/admin";
    }

    @GetMapping(value = "/user")
    public String getAdminUserPage(Model model) {
        model.addAttribute("users", userService.getListOfUsers());
        model.addAttribute("totalUsers", userService.countTotalUsers());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        model.addAttribute("totalOrders", orderService.countTotalOrders());
        return "admin/adminUser";
    }
    @GetMapping(value = "/user/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("messageDeleteUser", "Deleted user successfully!");
        return "redirect:/admin/user";
    }
    @PostMapping(value = "/user/search")
    public String searchUserByName(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("users", userService.findByNameContaining(keyword));
        return "admin/adminUser";
    }

    @GetMapping(value = "/product")
    public String getAdminProductPage(Model model) {
        model.addAttribute("products", productService.getListOfProducts());
        model.addAttribute("totalUsers", userService.countTotalUsers());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        model.addAttribute("totalOrders", orderService.countTotalOrders());
        return "admin/adminProduct";
    }
    @GetMapping(value = "/product/create")
    public String getCreateProduct(Model model, Product product) {
        model.addAttribute("product", product);
        return "admin/adminProductCreate";
    }
    @PostMapping(value = "/product/create")
    public String postCreateProduct(@ModelAttribute("product") Product product, Model model) {
        productService.save(product);
        model.addAttribute("messageSaveProduct", "Saved product successfully!");
        return "admin/adminProductCreate";
    }
    @PostMapping(value = "/product/search")
    public String searchProductByName(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("products", productService.findByNameContaining(keyword));
        return "admin/adminProduct";
    }
    @GetMapping(value = "/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productService.delete(id);
        model.addAttribute("messageDeleteProduct", "Deleted product successfully!");
        return "redirect:/admin/product";
    }

    @GetMapping(value = "/order")
    public String getAdminOrderPage(Model model) {
        model.addAttribute("orders", orderService.getListOfOrders());
        model.addAttribute("totalUsers", userService.countTotalUsers());
        model.addAttribute("totalProducts", productService.countTotalProducts());
        model.addAttribute("totalOrders", orderService.countTotalOrders());
        return "admin/adminOrder";
    }
    @PostMapping(value = "/order/search")
    public String searchOrderByName(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("orders", orderService.findByNameContaining(keyword));
        return "admin/adminOrder";
    }
}

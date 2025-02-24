package project01.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project01.ecommerce.model.*;
import project01.ecommerce.service.CartService;
import project01.ecommerce.service.OrderService;
import project01.ecommerce.service.ProductService;
import project01.ecommerce.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    public UserController(UserService userService, ProductService productService, CartService cartService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
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
    @GetMapping(value = "/cart")
    public String cartPage(Principal principal, Model model) {
        Optional<User> user = userService.findByUserName(principal.getName());
        Cart cart = user.get().getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user.get());
            cartService.createCart(cart);
            model.addAttribute("cartNull", "Cart is empty. Select any product at ");
        }
        List<CartItem> cartItems = cartService.getCartItems(cart.getId());
        double totalPrice = cartService.calculateTotalPrice(cartItems);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "cart";
    }
    @GetMapping(value = "/cart/delete/{id}")
    public String deleteCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(id);
        return "redirect:/cart";
    }
    @GetMapping(value = "/checkout")
    public String checkoutPage(Model model, Order order, Principal principal) {
//        Optional<User> user = userService.findByUserName(principal.getName());
//        if (user.isEmpty()) {
//            throw new RuntimeException("User not found!");
//        }
        Cart cart = userService.getCurrentUser().getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userService.getCurrentUser());
            cartService.createCart(cart);
        }
        List<CartItem> cartItems = cartService.getCartItems(cart.getId());
        double totalPrice = cartService.calculateTotalPrice(cartItems);
        model.addAttribute("order", order);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalCartItems", cartService.countCartItemsByCartId(cart.getId()));
        return "/checkout";
    }
    @PostMapping(value = "/checkout/create-order")
    public String createOrderPage(@ModelAttribute("order") Order order, Model model) {
        Cart cart = userService.getCurrentUser().getCart();
        List<CartItem> cartItems = cartService.getCartItems(cart.getId());
        double totalPrice = cartService.calculateTotalPrice(cartItems);
        order.setUser(userService.getCurrentUser());
        order.setTotalPrice(totalPrice);
        List<OrderDetail> orderDetails = cartItems.stream().map(item -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProduct(item.getProduct());
            orderDetail.setPriceAtTime(item.getProduct().getPrice());
            return orderDetail;
        }).collect(Collectors.toList());
        order.setOrderDetails(orderDetails);
        orderService.save(order);
        cartService.clearCartItems(cart.getId());
        model.addAttribute("message", "Create Order Successfully!");
        return "/checkout";
    }
}

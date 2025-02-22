package project01.ecommerce.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project01.ecommerce.model.*;
import project01.ecommerce.repository.CartItemRepository;
import project01.ecommerce.repository.CartRepository;
import project01.ecommerce.repository.ProductRepository;
import project01.ecommerce.repository.UserRepository;

import java.util.List;

@Service
public class CartServiceImp implements CartService{
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartServiceImp(UserRepository userRepository, CartItemRepository cartItemRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addToCart(Long productId, int quantity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("❗❗❗ User is not authenticated ❗❗❗");
        }
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        CartItem cartItem = new CartItem(cart,product,quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public Cart getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }

//    @Override
//    public List<CartItemDTO> getCartItems(Long cartId) {
//        return cartRepository.findCartItemsWithProduct(cartId);
//    }

    @Override
    public List<CartItem> getCartItemsFix(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }
}

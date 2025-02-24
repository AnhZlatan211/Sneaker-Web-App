package project01.ecommerce.service;

import project01.ecommerce.model.Cart;
import project01.ecommerce.model.CartItem;

import java.util.List;

public interface CartService {
    void createCart(Cart cart);
    void addToCart(Long productId, int quantity);
    void deleteCartItem(Long id);
    void clearCartItems(Long cartId);
    List<CartItem> getCartItems(Long cartId);
    Long countCartItemsByCartId(Long cartId);
    double calculateTotalPrice(List<CartItem> cartItems);

    //    List<CartItemDTO> getCartItems(Long cartId);
}

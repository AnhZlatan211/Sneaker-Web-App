package project01.ecommerce.service;

import project01.ecommerce.model.Cart;
import project01.ecommerce.model.CartItem;

import java.util.List;

public interface CartService {
    void addToCart(Long productId, int quantity);
    void deleteCartItem(Long id);
    Cart getCartByUser(Long userId);
    List<CartItem> getCartItemsFix(Long cartId);
    double calculateTotalPrice(List<CartItem> cartItems);

    //    List<CartItemDTO> getCartItems(Long cartId);

}

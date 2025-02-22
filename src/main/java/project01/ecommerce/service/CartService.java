package project01.ecommerce.service;

import project01.ecommerce.model.Cart;
import project01.ecommerce.model.CartItem;
import project01.ecommerce.model.CartItemDTO;

import java.util.List;

public interface CartService {
    void addToCart(Long productId, int quantity);
    Cart getCartByUser(Long userId);
//    List<CartItemDTO> getCartItems(Long cartId);
    List<CartItem> getCartItemsFix(Long cartId);
}

package project01.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project01.ecommerce.model.Cart;
import project01.ecommerce.model.CartItem;
import project01.ecommerce.model.CartItemDTO;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);

//    @Query("SELECT new project01.ecommerce.model.CartItemDTO(ci.quantity, p.name, p.price, p.stock, p.imageUrl, p.description)"
//            + "FROM CartItem c JOIN c.product p WHERE c.cart.id = :cartId")
//    List<CartItemDTO> findCartItemsWithProduct(@Param("cartId") Long cartId);
}

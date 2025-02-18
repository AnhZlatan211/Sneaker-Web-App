package project01.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project01.ecommerce.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}

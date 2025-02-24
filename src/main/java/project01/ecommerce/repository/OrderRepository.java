package project01.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project01.ecommerce.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}

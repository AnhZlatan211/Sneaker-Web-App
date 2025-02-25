package project01.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project01.ecommerce.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT u FROM Order u WHERE u.recipientName LIKE %:keyword%")
    List<Order> findByNameContaining(@Param("keyword") String keyword);
}

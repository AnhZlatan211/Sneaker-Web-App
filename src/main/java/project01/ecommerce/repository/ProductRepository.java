package project01.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project01.ecommerce.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT u FROM Product u WHERE u.name LIKE %:keyword%")
    List<Product> findByNameContaining(@Param("keyword") String keyword);
}

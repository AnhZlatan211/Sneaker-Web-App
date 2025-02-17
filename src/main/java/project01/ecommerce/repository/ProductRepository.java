package project01.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project01.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

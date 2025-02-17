package project01.ecommerce.service;

import project01.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> saveAll(List<Product> products);
    Product save(Product product);
    void delete(Long id);
    List<Product> getListOfProducts();
    List<Product> findByNameContaining(String keyword);
    Long countTotalProducts();
}

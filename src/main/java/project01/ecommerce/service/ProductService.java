package project01.ecommerce.service;

import project01.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> saveAll(List<Product> products);
    Product save(Product product);
    List<Product> getListOfProducts();
    void delete(Long id);
    Long countTotalProducts();
}

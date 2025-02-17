package project01.ecommerce.service;

import org.springframework.stereotype.Service;
import project01.ecommerce.model.Product;
import project01.ecommerce.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getListOfProducts() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Long countTotalProducts() {
        return productRepository.count();
    }
}

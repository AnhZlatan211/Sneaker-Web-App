package project01.ecommerce.service;

import project01.ecommerce.model.Order;
import project01.ecommerce.model.Product;

import java.util.List;

public interface OrderService {
    void save(Order order);
    List<Order> getListOfOrders();
    List<Order> findByNameContaining(String keyword);
    Long countTotalOrders();
}

package project01.ecommerce.service;

import org.springframework.stereotype.Service;
import project01.ecommerce.model.Order;
import project01.ecommerce.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<Order> getListOfOrders() {
        return orderRepository.findAll() ;
    }

    @Override
    public Long countTotalOrders() {
        return orderRepository.count();
    }
}

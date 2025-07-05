package com.bytebites.order_service.service;

import com.bytebites.order_service.model.Order;
import com.bytebites.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order placeOrder(Order order, String email) {
        order.setCustomerEmail(email);
        order.setStatus(Order.Status.PENDING);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByCustomer(String email) {
        return orderRepository.findByCustomerEmail(email);
    }
}

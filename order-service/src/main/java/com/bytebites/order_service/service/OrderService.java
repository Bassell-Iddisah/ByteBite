package com.bytebites.order_service.service;

import com.bytebites.order_service.model.Order;
import com.bytebites.order_service.model.OrderStatus;
import com.bytebites.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;

    public Order placeOrder(Order order, String Customeremail) {
        order.setCustomerEmail(Customeremail);
        order.setStatus(OrderStatus.PENDING);
        order.getItems().forEach(item -> item.setOrder(order));
        return orderRepo.save(order);
    }

    public List<Order> getOrdersByCustomer(String Customeremail) {
        return orderRepo.findByCustomerEmail(Customeremail);
    }

    public List<Order> getOrdersByRestaurant(Long restaurantId, String ownerEmail) {
        // Validate restaurant ownership
        return orderRepo.findByRestaurantId(restaurantId);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status, String ownerEmail) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        // Check if ownerEmail own restaurant
        order.setStatus(status);
        return orderRepo.save(order);
    }
}

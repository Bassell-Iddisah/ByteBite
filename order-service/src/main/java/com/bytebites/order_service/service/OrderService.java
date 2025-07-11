package com.bytebites.order_service.service;

import com.bytebites.order_service.dto.OrderRequestDTO;
import com.bytebites.order_service.model.Order;
import com.bytebites.order_service.model.OrderItem;
import com.bytebites.order_service.model.OrderStatus;
import com.bytebites.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepo;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public Order placeOrder(OrderRequestDTO orderRequestDTO, String Customeremail) {
        Order order = Order.builder()
                .customerEmail(Customeremail)
                .restaurantId(orderRequestDTO.getRestaurantId())
                .status(OrderStatus.PENDING)
                .build();

        List<OrderItem> items = orderRequestDTO.getItems().stream()
                .map(itemReq -> OrderItem.builder()
                        .menuItem(itemReq.getMenuItem())
                        .quantity(itemReq.getQuantity())
                        .price(itemReq.getPrice())
                        .order(order)
                        .build())
                .toList();
        order.setItems(items);
        logger.info("=== Build order for customer: {}", Customeremail);

        return orderRepo.save(order);
    }

    public List<Order> getOrdersByCustomer(String Customeremail) {
        logger.info("=== Get orders belonging to customer: {}", Customeremail);
        return orderRepo.findByCustomerEmail(Customeremail);
    }

    public List<Order> getOrdersByRestaurant(Long restaurantId, String ownerEmail) {
        // Validate restaurant ownership
        logger.info("=== Get orders belonging to restaurant: {}", restaurantId);
        return orderRepo.findByRestaurantId(restaurantId);
    }

    public Order updateOrderStatus(Long orderId, OrderStatus status, String ownerEmail) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        // Check if ownerEmail own restaurant
        order.setStatus(status);
        logger.info("=== Update order status for customer: {}", order.getCustomerEmail());
        return orderRepo.save(order);
    }
}

package com.bytebites.order_service.controller;

import com.bytebites.order_service.dto.OrderRequestDTO;
import com.bytebites.order_service.model.Order;
import com.bytebites.order_service.model.OrderStatus;
import com.bytebites.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class orderController {

    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(orderController.class);

    @GetMapping("/home")
    public String home() {
        return "Welcome to Orders home";
    }

    @PostMapping("/placeorder")
    public Order placeOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO,
                            @RequestHeader("X-User-Email") String Cemail){
        logger.info("=== Placing order for user: {}", Cemail);
        return orderService.placeOrder(orderRequestDTO, Cemail);
    }

//    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/myOrders")
    public List<Order> myOrders(@RequestHeader("X-User-Email") String Cemail) {
        logger.info("=== Getting orders for user: {}", Cemail);
        return orderService.getOrdersByCustomer(Cemail);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Order> ordersForRestaurant(@PathVariable("restaurantId") Long restaurantId,
                                           @RequestHeader("X-User-Email") String ownerEmail) {
        logger.info("=== Getting orders for restaurant ID: {}", restaurantId);
        return orderService.getOrdersByRestaurant(restaurantId, ownerEmail);
    }

    @PutMapping("/{orderId}/status")
    public Order updateStatus(@PathVariable Long orderId,
                              @RequestParam OrderStatus status,
                              @RequestHeader("X-User-Email") String ownerEmail) {
        logger.info("=== Updating status for order: {}", orderId);
        return orderService.updateOrderStatus(orderId, status, ownerEmail);
    }
}

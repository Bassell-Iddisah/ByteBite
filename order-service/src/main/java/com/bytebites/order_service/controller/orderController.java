package com.bytebites.order_service.controller;

import com.bytebites.order_service.model.Order;
import com.bytebites.order_service.model.OrderStatus;
import com.bytebites.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class orderController {

    private final OrderService orderService;

    @GetMapping("/home")
    public String home() {
        return "Welcome to Orders home";
    }

    @PostMapping("/placeorder")
    public Order placeOrder(@RequestBody Order order,
                            @RequestHeader("X-User-Email") String Cemail){
        return orderService.placeOrder(order, Cemail);
    }

//    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/myOrders")
    public List<Order> myOrders(@RequestHeader("X-User-Email") String Cemail) {
        return orderService.getOrdersByCustomer(Cemail);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Order> ordersForRestaurant(@PathVariable("restaurantId") Long restaurantId,
                                           @RequestHeader("X-User-Email") String ownerEmail) {
        return orderService.getOrdersByRestaurant(restaurantId, ownerEmail);
    }

    @PutMapping("/{orderId}/status")
    public Order updateStatus(@PathVariable Long orderId,
                              @RequestParam OrderStatus status,
                              @RequestHeader("X-User-Email") String ownerEmail) {
        return orderService.updateOrderStatus(orderId, status, ownerEmail);
    }
}

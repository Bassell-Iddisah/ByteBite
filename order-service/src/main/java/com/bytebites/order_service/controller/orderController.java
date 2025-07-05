package com.bytebites.order_service.controller;

import com.bytebites.order_service.model.Order;
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

    @PostMapping("")
//    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public List<Order> myOrders(@RequestHeader("X-User-Email") String email) {
        return orderService.getOrdersByCustomer(email);
    }
}

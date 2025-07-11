package com.bytebites.order_service.repository;

import com.bytebites.order_service.model.Order;
import jakarta.persistence.EnumType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerEmail(String email);
    List<Order> findByRestaurantId(Long restaurantId);
    List<Order> findByStatus(EnumType status);
}

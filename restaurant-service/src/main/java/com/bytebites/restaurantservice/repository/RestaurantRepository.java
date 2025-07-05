package com.bytebites.restaurantservice.repository;

import com.bytebites.restaurantservice.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByOwnerEmail(String email);
    Optional<Restaurant> findRestaurantById(Long restaurantId);
}

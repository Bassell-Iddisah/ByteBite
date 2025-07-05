package com.bytebites.restaurantservice.controller;

import com.bytebites.restaurantservice.model.Restaurant;
import com.bytebites.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/home")
    public String restaurant_home() {
        return "Welcome to Restaurant Service!!!";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_RESTAURANT_OWNER')")
    public Restaurant create(@RequestBody Restaurant restaurant,
                             @RequestHeader("X-User-Email") String email) {
        return restaurantService.save(restaurant, email);
    }

    @GetMapping
    public List<Restaurant> all() {
        return restaurantService.getAll();
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('ROLE_RESTAURANT_OWNER')")
    public List<Restaurant> mine(@RequestHeader("X-User-Email") String email) {
        return restaurantService.getByOwner(email);
    }
}

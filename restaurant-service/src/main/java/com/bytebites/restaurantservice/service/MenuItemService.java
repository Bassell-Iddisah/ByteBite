package com.bytebites.restaurantservice.service;

import com.bytebites.restaurantservice.model.MenuItem;
import com.bytebites.restaurantservice.model.Restaurant;
import com.bytebites.restaurantservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.bytebites.restaurantservice.repository.MenuitemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuitemRepository menuItemRepo;
    private final RestaurantRepository restaurantRepo;

    public MenuItem addMenuItem(Long restaurantId, MenuItem item, String ownerEmail) {
        Restaurant restaurant = restaurantRepo.findRestaurantById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        if(!restaurant.getOwnerEmail().equals(ownerEmail)) {
            throw new RuntimeException("You're not the owner, get out!!");
        }

        item.setRestaurant(restaurant);
        return menuItemRepo.save(item);
    }

    public List<MenuItem> getMenuForRestaurant(Long restaurantId) {
        return menuItemRepo.findByRestaurantId(restaurantId);
    }
}

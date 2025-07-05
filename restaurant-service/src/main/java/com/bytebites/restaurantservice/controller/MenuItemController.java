package com.bytebites.restaurantservice.controller;


import lombok.*;
import org.springframework.web.bind.annotation.*;

import com.bytebites.restaurantservice.service.MenuItemService;
import com.bytebites.restaurantservice.model.MenuItem;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menu")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    //    @PreAuthorize("hasRole('ROLE_CUSTOMER_OWNER)")
    @PostMapping
    public MenuItem addMenu(@PathVariable() Long restaurantid,
                            @RequestBody MenuItem item,
                            @RequestHeader("X-User-Email") String email) {
        return menuItemService.addMenuItem(restaurantid, item, email);
    }
}

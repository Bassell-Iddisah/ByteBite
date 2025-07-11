package com.bytebites.order_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderItemRequest {

    @NotBlank
    private String menuItem;

    @Min(1)
    private int quantity;

    @Min(0)
    private double price;
}


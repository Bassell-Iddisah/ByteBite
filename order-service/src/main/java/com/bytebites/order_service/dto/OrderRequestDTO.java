package com.bytebites.order_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    @NotNull
    @Min(1)
    @Positive(message="Id must be any whole number")
    private Long restaurantId;

    @Size(min = 1, message="Must have at least one element")
    private List<OrderItemRequest> items;
}
package com.bytebites.order_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderItemId;

    private String menuItem;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name="Orderid")
    @JsonBackReference
    private Order order;
}

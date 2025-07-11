package com.bytebites.order_service.service;

import com.bytebites.order_service.dto.OrderItemRequest;
import com.bytebites.order_service.dto.OrderRequestDTO;
import com.bytebites.order_service.model.Order;
import com.bytebites.order_service.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class OrderServiceIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("orderdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private OrderService orderService;

    @Test
    void testPlaceOrder_integration() {
        OrderItemRequest item = new OrderItemRequest();
        item.setMenuItem("Banku");
        item.setPrice(10.0);
        item.setQuantity(2);

        OrderRequestDTO request = new OrderRequestDTO();
        request.setRestaurantId(1L);
        request.setItems(List.of(item));

        Order saved = orderService.placeOrder(request, "test@bytebites.com");

        assertNotNull(saved.getId());
        assertEquals("test@bytebites.com", saved.getCustomerEmail());
        assertEquals(1, saved.getItems().size());
    }
}

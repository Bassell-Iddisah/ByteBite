package com.bytebites.order_service.service;

import com.bytebites.order_service.dto.OrderItemRequest;
import com.bytebites.order_service.dto.OrderRequestDTO;
import com.bytebites.order_service.model.Order;
import com.bytebites.order_service.model.OrderItem;
import com.bytebites.order_service.model.OrderStatus;
import com.bytebites.order_service.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceOrder_success() {
        // Arrange
        OrderItemRequest item = new OrderItemRequest();
        item.setMenuItem("Jollof");
        item.setPrice(20.0);
        item.setQuantity(1);

        OrderRequestDTO request = new OrderRequestDTO();
        request.setRestaurantId(1L);
        request.setItems(List.of(item));

        Order savedOrder = Order.builder()
                .id(1L)
                .customerEmail("user@bytebites.com")
                .restaurantId(1L)
                .status(OrderStatus.PENDING)
                .build();

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // Act
        Order result = orderService.placeOrder(request, "user@bytebites.com");

        // Assert
        assertNotNull(result);
        assertEquals("user@bytebites.com", result.getCustomerEmail());
        assertEquals(OrderStatus.PENDING, result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testPlaceOrder_nullRestaurantId_throwsException() {
        // Arrange
        OrderRequestDTO request = new OrderRequestDTO();
        request.setRestaurantId(null);
        request.setItems(List.of());

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                orderService.placeOrder(request, "user@bytebites.com"));
    }
}

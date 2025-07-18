package com.example.orderapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        Order order1 = new Order(1L, "test1@example.com", "Description 1");
        Order order2 = new Order(2L, "test2@example.com", "Description 2");

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();

        assertEquals(2, orders.size());
        assertEquals("test1@example.com", orders.get(0).getEmail());
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order(1L, "test@example.com", "Test Description");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById(1L);

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
    }

    @Test
    public void testCreateOrder() {
        Order orderToCreate = new Order(null, "new@example.com", "New Description");
        Order createdOrder = new Order(1L, "new@example.com", "New Description");

        when(orderRepository.save(orderToCreate)).thenReturn(createdOrder);

        Order result = orderService.createOrder(orderToCreate);

        assertEquals(1L, result.getId());
        assertEquals("new@example.com", result.getEmail());
    }
}
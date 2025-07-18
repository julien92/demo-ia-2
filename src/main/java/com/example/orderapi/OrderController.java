package com.example.orderapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The OrderController class provides REST API endpoints for managing orders.
 * It acts as a front-facing interface for handling HTTP requests related to orders,
 * including retrieving and creating orders.
 *
 * Endpoints:
 * - GET /orders/{id}: Retrieves an order by its ID.
 * - GET /orders: Retrieves a default order (compatibility endpoint for legacy systems).
 * - POST /orders: Creates a new order.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Retrieves an order by its unique ID.
     *
     * @param id the unique identifier of the order to be retrieved
     * @return a ResponseEntity containing the order if found, or a 404 Not Found response if no order
     *         exists for the provided ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrder(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    /**
     * Compatibility endpoint for retrieving a default order.
     * This method is intended for legacy systems that do not provide an order ID.
     *
     * @return the default order object
     */
    // Endpoint de compatibilité pour les anciens appels
    @GetMapping
    public Order getOrder() {
        return orderService.getOrder();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order orderRequest) {
        Order createdOrder = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}

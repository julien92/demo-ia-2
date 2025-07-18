package com.example.orderapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  /**
   * Tests pour la méthode getOrder() sans paramètre (compatibilité).
   */
  @Test
  void testGetOrder_ReturnsPredefinedOrder() {
    // Act
    Order order = orderService.getOrder();

    // Assert
    assertNotNull(order, "Order should not be null");
    assertEquals(1L, order.getId(), "Order ID should be 1L");
    assertEquals("client@email.com", order.getEmail(), "Email should match the predefined value");
    assertEquals("Commande pour un superbe produit", order.getDescription(),
        "Description should match the predefined value");
  }

  /**
   * Tests pour la méthode getOrder(Long id) avec un id existant.
   */
  @Test
  void testGetOrderById_ReturnsCorrectOrder() {
    // Arrange - Créer une commande pour s'assurer qu'elle existe
    orderService.getOrder(); // Initialise la commande avec ID 1L

    // Act
    Order order = orderService.getOrder(1L);

    // Assert
    assertNotNull(order, "Order should not be null");
    assertEquals(1L, order.getId(), "Order ID should be 1L");
    assertEquals("client@email.com", order.getEmail(), "Email should match the expected value");
    assertEquals("Commande pour un superbe produit", order.getDescription(),
        "Description should match the expected value");
  }

  /**
   * Tests pour la méthode getOrder(Long id) avec un id non existant.
   */
  @Test
  void testGetOrderById_NonExistingId_ReturnsNull() {
    // Act
    Order order = orderService.getOrder(999L);

    // Assert
    assertNull(order, "Order should be null for non-existing ID");
  }

  /**
   * Tests for the createOrder method in the OrderService class.
   * <p>
   * Method being tested: public Order createOrder(Order orderRequest)
   * <p>
   * Purpose of the method: Creates a new Order object with dynamically assigned id while retaining
   * the email and description values from the input object.
   */
  @Test
  void testCreateOrder_ReturnsNewOrderWithProvidedDetails() {
    // Arrange
    Order orderRequest = new Order(null, "newclient@email.com", "New product order");

    // Act
    Order createdOrder = orderService.createOrder(orderRequest);

    // Assert
    assertNotNull(createdOrder, "Created Order should not be null");
    assertNotNull(createdOrder.getId(), "New Order ID should not be null");
    assertEquals("newclient@email.com", createdOrder.getEmail(),
        "Email should match the provided value");
    assertEquals("New product order", createdOrder.getDescription(),
        "Description should match the provided value");
  }
}
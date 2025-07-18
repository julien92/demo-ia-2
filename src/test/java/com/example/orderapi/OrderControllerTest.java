package com.example.orderapi;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private OrderService orderService;

  /**
   * Test pour la compatibilité avec l'ancienne méthode getOrder() sans paramètre.
   */
  @Test
  public void testGetOrder_Success() throws Exception {
    // Given
    Order mockOrder = new Order(1L, "test@email.com", "Test description");
    when(orderService.getOrder()).thenReturn(mockOrder);

    // When & Then
    mockMvc.perform(get("/orders")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.email").value("test@email.com"))
        .andExpect(jsonPath("$.description").value("Test description"));
  }

  /**
   * Test pour vérifier que la récupération d'un order par ID fonctionne correctement.
   */
  @Test
  public void testGetOrderById_Success() throws Exception {
    // Given
    Order mockOrder = new Order(1L, "test@email.com", "Test description");
    when(orderService.getOrder(1L)).thenReturn(mockOrder);

    // When & Then
    mockMvc.perform(get("/orders/1")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.email").value("test@email.com"))
        .andExpect(jsonPath("$.description").value("Test description"));
  }

  /**
   * Test pour vérifier que la récupération d'un order inexistant retourne une erreur 404.
   */
  @Test
  public void testGetOrderById_NotFound() throws Exception {
    // Given
    when(orderService.getOrder(anyLong())).thenReturn(null);

    // When & Then
    mockMvc.perform(get("/orders/999")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  /**
   * Test pour vérifier la création d'un order.
   */
  @Test
  public void testCreateOrder_Success() throws Exception {
    // Given
    Order requestOrder = new Order(null, "new@email.com", "New order description");
    Order createdOrder = new Order(123L, "new@email.com", "New order description");

    when(orderService.createOrder(org.mockito.ArgumentMatchers.any(Order.class)))
        .thenReturn(createdOrder);

    // When & Then
    mockMvc.perform(post("/orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\":\"new@email.com\",\"description\":\"New order description\"}")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(123))
        .andExpect(jsonPath("$.email").value("new@email.com"))
        .andExpect(jsonPath("$.description").value("New order description"));
  }
}
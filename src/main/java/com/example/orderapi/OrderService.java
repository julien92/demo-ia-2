package com.example.orderapi;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

  private final Map<Long, Order> orders = new HashMap<>();

  public Order getOrder(Long id) {
    if (!orders.containsKey(id) && id == 1L) {
      Order order = new Order(1L, "client@email.com", "Commande pour un superbe produit");
      orders.put(1L, order);
    }
    return orders.get(id);
  }

  // Méthode de compatibilité pour les anciens appels
  public Order getOrder() {
    return getOrder(1L);
  }

  public Order createOrder(Order orderRequest) {
    Long newId = System.currentTimeMillis();
    Order order = new Order(
        newId,
        orderRequest.getEmail(),
        orderRequest.getDescription()
    );
    orders.put(newId, order);
    return order;
  }
}
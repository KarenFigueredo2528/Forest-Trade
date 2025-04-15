package com.unbosque.edu.co.forest.repository;

import com.unbosque.edu.co.forest.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Buscar todas las órdenes por usuario
    List<Order> findByUserId(Long userId);

    // Buscar una orden por su ID de Alpaca
    Order findByAlpacaOrderId(String alpacaOrderId);

    // Buscar todas las órdenes por estado
    List<Order> findByOrderStatus(com.unbosque.edu.co.forest.model.enums.OrderStatus status);

    // Buscar órdenes que requieren firma
    List<Order> findByRequiresSignatureTrue();

}

package com.unbosque.edu.co.forest.repository;

import com.unbosque.edu.co.forest.model.entity.Order;
import com.unbosque.edu.co.forest.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Buscar todas las órdenes por usuario
    List<Order> findByUserId(Long userId);

    // Buscar una orden por su ID de Alpaca
    Order findByAlpacaOrderId(String alpacaOrderId);


    // Buscar órdenes que requieren firma por comisionista
    List<Order> findByStockbrokerIdAndRequiresSignatureIsTrue(Long userId);

    // Buscar ordenes por usuario y estado
    List<Order> findByUserIdAndOrderStatus(Integer userId, OrderStatus orderStatus);

    // Buscar ordenes por usuario y que no requieren firma del comisionista
    List<Order> findByUserIdAndRequiresSignatureIsFalse(OrderStatus orderStatus);


}

package com.unbosque.edu.co.forest.controller;

import com.unbosque.edu.co.forest.exception.CustomAlpacaException;
import com.unbosque.edu.co.forest.model.dto.OrderRequestDTO;
import com.unbosque.edu.co.forest.model.entity.Order;
import com.unbosque.edu.co.forest.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/buy")
    public ResponseEntity<Order> createBuyOrder(@RequestBody OrderRequestDTO requestDTO) {
        Order newOrder = orderService.createBuyOrder(requestDTO);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    // Manejo de errores específicos de Alpaca
    @ExceptionHandler(CustomAlpacaException.class)
    public ResponseEntity<String> handleAlpacaException(CustomAlpacaException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body("{Error_Alpaca: " + ex.getAlpacaMessage()+"}");
    }
}

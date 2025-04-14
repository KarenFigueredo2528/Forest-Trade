package com.unbosque.edu.co.forest.controller;

import com.unbosque.edu.co.forest.model.dto.StockDTO;
import com.unbosque.edu.co.forest.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StocksController {

    private final StockService stockService;

    @Autowired
    public StocksController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<StockDTO> getAllStocks() throws Exception {
        return stockService.getAllStocks();
    }

}

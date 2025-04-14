package com.unbosque.edu.co.forest.repository;

import com.unbosque.edu.co.forest.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  MarketRepository extends JpaRepository<Market, Integer> {

    Optional<Market> findByMarketCode(String marketCode);
}

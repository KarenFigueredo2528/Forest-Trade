package com.unbosque.edu.co.forest.repository;

import com.unbosque.edu.co.forest.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findByUserId(Integer userId);

    List<Transaction> findByOrderId(Integer orderId);

    List<Transaction> findByUserIdOrderByCreatedAtDesc(Integer userId);
}

package com.unbosque.edu.co.forest.service;

import com.unbosque.edu.co.forest.model.dto.TransactionDTO;
import com.unbosque.edu.co.forest.model.entity.Transaction;
import com.unbosque.edu.co.forest.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    public TransactionDTO createTransaction(Transaction transaction) {
        return modelMapper.map(
                transactionRepository.save(
                                transaction
                        )
                , TransactionDTO.class);
    }
}

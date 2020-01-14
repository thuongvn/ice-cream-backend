package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.entity.TransactionDetail;
import com.example.demo.model.detail.TransactionDetailDto;
import com.example.demo.model.detail.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public TransactionDto createTransaction(int id_user);
    public List<TransactionDto> listTransaction(int id_user, int page);
    public List<TransactionDetailDto> getListTransactionDetail(int transaction_id);
}

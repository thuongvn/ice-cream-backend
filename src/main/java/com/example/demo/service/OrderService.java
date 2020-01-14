package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.entity.TransactionDetail;
import com.example.demo.model.detail.ListTransactionDto;
import com.example.demo.model.detail.TransactionDetailDto;
import com.example.demo.model.detail.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public TransactionDto createTransaction(int id_user);
    public List<TransactionDto> listTransaction(int id_user, int page);
    public List<TransactionDetailDto> getListTransactionDetail(int transaction_id);

    //for mobile
    public TransactionDto createTransactionMobile(int id_user, int quantity, int product);
    //for store
    public ListTransactionDto getAllTransactionForStore(int page);
    //for customer
    public ListTransactionDto getAllTransactionForUser(int user_id, int page);
    //update status of order
    public TransactionDto updateStatusOfOrder(int order_id, int status);
}

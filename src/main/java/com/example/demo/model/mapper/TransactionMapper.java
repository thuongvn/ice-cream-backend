package com.example.demo.model.mapper;

import com.example.demo.entity.Transaction;
import com.example.demo.entity.TransactionDetail;
import com.example.demo.model.detail.TransactionDetailDto;
import com.example.demo.model.detail.TransactionDto;

public class TransactionMapper {
    public static TransactionDto toTransactionDto(Transaction transaction){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setCreate_at(transaction.getCreate_at());
        transactionDto.setAddress(transaction.getAddress());
        transactionDto.setTotal_price(transaction.getTotal_price());
        transactionDto.setStatus(transaction.getStatus());
        transactionDto.setUpdate_at(transaction.getUpdate_at());

        return transactionDto;
    }

    public static TransactionDetailDto transactionDetailDto(TransactionDetail transactionDetail){
        TransactionDetailDto transactionDetailDto = new TransactionDetailDto();
        transactionDetailDto.setId(transactionDetail.getId());
        transactionDetailDto.setCreate_at(transactionDetail.getCreate_at());
        transactionDetailDto.setProduct_id(transactionDetail.getProduct().getId());
        transactionDetailDto.setProduct_name(transactionDetail.getProduct().getProduct_name());
        transactionDetailDto.setQuantity(transactionDetail.getQuantity());
        transactionDetailDto.setTotal_money(transactionDetail.getTotal_money());
        transactionDetailDto.setUpdate_at(transactionDetail.getUpdate_at());
        transactionDetailDto.setTransaction_id(transactionDetail.getTransaction().getId());

        return transactionDetailDto;
    }
}

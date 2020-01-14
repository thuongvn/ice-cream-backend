package com.example.demo.model.detail;

import lombok.Data;

import java.util.List;

@Data
public class ListTransactionDto {
    private List<TransactionDto> transactionDtos;
    private int totalPages;
    private long totalItems;
}

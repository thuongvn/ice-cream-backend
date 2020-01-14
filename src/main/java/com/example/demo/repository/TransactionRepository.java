package com.example.demo.repository;

import com.example.demo.entity.Transaction;
import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM transaction WHERE user_id LIKE %?1%")
    public Page<Transaction> listTransactionByUser(int user_id, Pageable pageable);

}

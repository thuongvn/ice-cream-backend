package com.example.demo.repository;

import com.example.demo.entity.TransactionDetail;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM transaction_detail WHERE transaction_id LIKE %?1%")
    public List<TransactionDetail> findTransactionDetailByTransactionId(int transaction_id);
}

package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "transaction_detail")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name="create_at")
    private Date create_at;

    @Column(name="update_at")
    private Date update_at;

    @Column(name="total_money")
    private float total_money;
}

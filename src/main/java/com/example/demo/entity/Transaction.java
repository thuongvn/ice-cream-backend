package com.example.demo.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name="create_at")
    private Date create_at;

    @Column(name="update_at")
    private Date update_at;
    @Column(name = "total_price")
    private float total_price;

    @Column(name = "address")
    private String address;

    @Column(name = "location")
    private String location;


}

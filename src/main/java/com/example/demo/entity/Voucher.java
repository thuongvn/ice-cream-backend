package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "discount")
    private float discount;

    @Column(name="update_at")
    private Date update_at;

    @Column(name = "total_price")
    private float total_price;

    @OneToMany(mappedBy = "voucher")
    private List<VoucherOfUser> voucherOfUsers;
}

package com.example.demo.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(name="email", unique = true)
    private String email;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="full_name")
    private String full_name;

    @NotNull
    @Column(name="numberphone")
    private String numberphone;

    @Column(name="address")
    private String address;

    @Column(name="avatar")
    private String avatar;

    @Column(name="gender")
    private Boolean gender;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="role")
    private String roles;

    @Column(name="status")
    private Boolean status;

    @OneToMany(mappedBy = "user")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;



    @OneToMany(mappedBy = "user")
    private List<VoucherOfUser> voucherOfUsers;

    @OneToMany(mappedBy = "user")
    private List<Store> stores;
}

package com.example.demo.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String addres;

    @Column(name = "location")
    private String location;

    @OneToMany
    private List<StoreHaveProduct> storeHaveProducts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "total_money")
    private float total_money;

}

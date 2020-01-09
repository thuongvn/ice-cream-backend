package com.example.demo.entity;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TermVector;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Indexed
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Field(termVector = TermVector.YES, analyze= Analyze.YES, store= Store.NO)
    @Column(name="product_name")
    private String product_name;


    @Column(name="description")
    private String description;

    @Column(name="images")
    private String images;

    @Column(name="create_at")
    private Date create_at;

    @Column(name="update_at")
    private Date update_at;

    @Column(name="price")
    private float price;


    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    private List<TransactionDetail> transactionDetails;

    @OneToMany(mappedBy = "product")
    private List<StoreHaveProduct> storeHaveProducts;
}

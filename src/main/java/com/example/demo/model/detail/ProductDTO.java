package com.example.demo.model.detail;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
public class ProductDTO {
    private int id;
    private String product_name;
    private String description;
    private String images;
    private Date create_at;
    private Date update_at;
    private float price;
}

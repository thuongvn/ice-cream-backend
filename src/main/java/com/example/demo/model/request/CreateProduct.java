package com.example.demo.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class CreateProduct {
    private int id;
    @NotNull(message = "product_name is required")
    @NotEmpty(message = "product_name is required")
    @ApiModelProperty(
            example="Kem Trang Tien",
            notes="product_name cannot be empty",
            required=true
    )
    private String product_name;
    private String description;
    @Valid
    @URL(regexp="(https?:\\/\\/.*\\.(?:png|jpg))", message="Images must be an url image")
    @ApiModelProperty(
            example="https://ssl.gstatic.com/images/branding/product/1x/avatar_circle_blue_512dp.png",
            notes="Avatar must be an url image",
            required=false
    )
    private String images;
    private float price;
}

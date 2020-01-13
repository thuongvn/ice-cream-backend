package com.example.demo.model.request;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCartItem {
    @NotNull(message = "product is required")
    @ApiModelProperty(
            example="sam.smith@gmail.com",
            notes="Enter product needed exit",
            required=true
    )
    private int product;
    @NotNull(message = "Email is required")
    @Email(message = "Please provide a valid email")
    @ApiModelProperty(
            example="sam.smith@gmail.com",
            notes="Email cannot be empty",
            required=true
    )
    private String email;

    private int quantity;
}

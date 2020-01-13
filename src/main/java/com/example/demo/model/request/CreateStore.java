package com.example.demo.model.request;

import com.example.demo.entity.StoreHaveProduct;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data

public class CreateStore {

    @NotNull(message = "Full name is required")
    @NotEmpty(message = "Full name is required")
    @ApiModelProperty(
            example="Sam Smith",
            notes="Full name cannot be empty",
            required=true
    )
    private String name;

    private String address;

    private String location;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    @ApiModelProperty(
            example="sam.smith@gmail.com",
            notes="Email cannot be empty",
            required=true
    )
    private String email;
}

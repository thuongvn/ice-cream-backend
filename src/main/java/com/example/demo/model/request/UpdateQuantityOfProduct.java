package com.example.demo.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuantityOfProduct {
    @NotNull(message = "Id is required")
    @NotEmpty(message = "Please provide a id already")

    @ApiModelProperty(
            required=true
    )
    private int id;

    @NotNull(message = "Quantity is required")
    @NotEmpty(message = "Please provide a quantity of product on this store")

    @ApiModelProperty(
            required=true
    )
    private int quantity;
}

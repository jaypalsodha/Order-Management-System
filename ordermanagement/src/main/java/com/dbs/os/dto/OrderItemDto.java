package com.dbs.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * created by jaypal sodha
 */
@Getter @Setter @NoArgsConstructor
public class OrderItemDto {

    @Min(0)
    @Max(10)
    private Integer productQuantity;

    @Size(max = 255)
    private String productName;

    @NotNull
    private Integer productCode;
         
    public OrderItemDto(Integer productQuantity, String productName, Integer productCode) {
		super();
		this.productQuantity = productQuantity;
		this.productName = productName;
		this.productCode = productCode;
	}
}

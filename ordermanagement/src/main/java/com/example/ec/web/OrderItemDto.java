package com.example.ec.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * created by jaypal sodha
 */
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
    
    protected OrderItemDto() {}
    
	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}	
}

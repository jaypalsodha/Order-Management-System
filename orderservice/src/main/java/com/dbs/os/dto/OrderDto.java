package com.dbs.os.dto;

import java.util.List;

import javax.validation.constraints.Size;

import com.dbs.os.domain.OrderItem;
import com.dbs.os.domain.ProductCode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 	author, jaypal sodha
 * 
 */
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class OrderDto {
	
	@Size(max = 255)
    private String customerName;
    
    private String orderDate;
    
    private String shippingAddress;
    
    private List<OrderItem> orderItemList;
    private List<ProductCode> productCodeList;
    
    private double total;
       
	public OrderDto(String customerName, String orderDate, String shippingAddress, List<OrderItem> orderItemList,
			double total) {		
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.orderItemList = orderItemList;
		this.total = total;
	}
}
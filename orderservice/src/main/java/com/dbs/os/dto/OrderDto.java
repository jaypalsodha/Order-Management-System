package com.example.ec.web;

import java.util.List;

import javax.validation.constraints.Size;

import com.example.ec.domain.OrderItem;

/**
 * 
 */
public class OrderDto {
	
  
	@Size(max = 255)
    private String customerName;
    
    private String orderDate;
    
    private String shippingAddress;
    
    private List<OrderItem> orderItemList;
    
    private double total;
       
    protected OrderDto() {}
	public OrderDto(String customerName, String orderDate, String shippingAddress, List<OrderItem> orderItemList,
			double total) {		
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.orderItemList = orderItemList;
		this.total = total;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
    
    
   
}
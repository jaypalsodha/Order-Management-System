package com.dbs.os.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 
 * Created by jaypal sodha
 */
@Entity
@Table(name="order1")
@Getter @Setter @NoArgsConstructor
public class Order implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column
    private String customerName;
    
    @Column
    private String orderDate;
    
    @Column
    private String shippingAddress;
    
    @OneToMany(mappedBy = "order1", orphanRemoval = true)
    private List<OrderItem> orderItemList;
    
    @Column 
    private double total;
    
	public Order(String customerName, String orderDate, String shippingAddress, List<OrderItem> orderItemList,
			double total) {
		super();
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.orderItemList = orderItemList;
		this.total = total;
	}
	public void addOrderItem(OrderItem orderItem) {
		orderItemList.add(orderItem);
	}
	public void removeOrderItem(OrderItem orderItem) {
		orderItemList.remove(orderItem);
	}
}

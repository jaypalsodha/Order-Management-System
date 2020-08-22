package com.dbs.os.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jaypal
 *
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class OrderItem implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    private Integer productCode;

    @Column
    private String productName;
    
    @Column 
    private Integer productQuantity;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order1;

	public OrderItem(Integer productCode, String productName, Integer productQuantity) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productQuantity = productQuantity;
	}
}

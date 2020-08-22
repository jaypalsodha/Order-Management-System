package com.dbs.os.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * Created by jaypal sodha
 */
@Entity
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    private Integer productCode;

    @Column
    private String productName;
    
    @Column 
    private Integer productQuantity;

	public OrderItem(Integer productCode, String productName, Integer productQuantity) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productQuantity = productQuantity;
	}
}

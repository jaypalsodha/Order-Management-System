/**
 * 
 */
package com.dbs.os.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.os.domain.OrderItem;

/**
 * @author jaypal sodha
 *
 */
public interface OrderRepository extends CrudRepository<OrderItem, Integer>{
	
	public OrderItem findByProductCode(Integer productCode);
}

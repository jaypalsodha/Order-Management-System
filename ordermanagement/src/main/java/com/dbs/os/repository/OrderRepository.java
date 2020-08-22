/**
 * 
 */
package com.dbs.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.domain.OrderItem;

/**
 * @author jaypal sodha
 *
 */
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{
	
	public OrderItem findByProductCode(Integer productCode);
}

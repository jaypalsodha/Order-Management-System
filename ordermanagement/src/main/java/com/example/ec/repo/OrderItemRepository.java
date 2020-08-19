/**
 * 
 */
package com.example.ec.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.ec.domain.OrderItem;

/**
 * @author jaypal sodha
 *
 */
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>{
	
	public OrderItem findByProductCode(Integer productCode);
}

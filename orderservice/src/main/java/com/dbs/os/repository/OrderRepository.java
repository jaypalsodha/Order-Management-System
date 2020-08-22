/**
 * 
 */
package com.example.ec.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.ec.domain.Order;

/**
 * @author jaypal sodha
 *
 */
public interface OrderRepository extends CrudRepository<Order, Integer>{
	
	public Order findByCustomerName(String customerName);
}

/**
 * 
 */
package com.dbs.os.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.os.domain.Order;

/**
 * @author jaypal sodha
 *
 */
public interface OrderRepository extends CrudRepository<Order, Integer>{
	
	public Order findByCustomerName(String customerName);
}

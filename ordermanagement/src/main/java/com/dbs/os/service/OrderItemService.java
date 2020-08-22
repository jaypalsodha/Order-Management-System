package com.dbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.domain.OrderItem;
import com.dbs.repository.OrderItemRepository;

/**
 * @author jaypal
 *
 */
@Service
public class OrderItemService {
    
	private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    
    public OrderItem createOrderItem(Integer productCode, String productName, Integer productQuantity) {
    	if (orderItemRepository.findOne(productCode) == null) {
            return orderItemRepository.save(new OrderItem(productCode, productName,productQuantity));
        } 
    	else {
            return null;
        }
    }
    public OrderItem findByProductCode(Integer productCode) {
    	return orderItemRepository.findByProductCode(productCode);
    }
    
    public Iterable<OrderItem> lookup(){
        return orderItemRepository.findAll();
    }
    
    public long total() {
        return orderItemRepository.count();
    }
}


package com.dbs.os.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.os.domain.OrderItem;
import com.dbs.os.repository.OrderRepository;

/**
 * @author jaypal sodha
 *
 */
@Service
public class OrderItemService {
    
	private OrderRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public OrderItem createOrderItem(Integer productCode, String productName, Integer productQuantity) {
    	if (orderItemRepository.findByProductCode(productCode) == null) {
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


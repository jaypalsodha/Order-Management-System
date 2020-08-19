package com.example.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec.domain.OrderItem;
import com.example.ec.repo.OrderItemRepository;

/**
 * OrderItem service
 *
 * Created by jaypal sodha
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
        } else {
            return null;
        }
    }        
    public Iterable<OrderItem> lookup(){
        return orderItemRepository.findAll();
    }
    public long total() {
        return orderItemRepository.count();
    }
}


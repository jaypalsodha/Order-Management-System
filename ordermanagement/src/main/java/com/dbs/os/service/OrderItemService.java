package com.dbs.os.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.os.domain.OrderItem;
import com.dbs.os.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jaypal sodha
 *
 */
@Service
@Slf4j
public class OrderItemService {

	private OrderRepository orderItemRepository;

	@Autowired
	public OrderItemService(OrderRepository orderItemRepository) {
		this.orderItemRepository = orderItemRepository;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public OrderItem createOrderItem(Integer productCode, String productName, Integer productQuantity) {
		log.info("inside createOrderItem for class :: OrderItemService");
		if (orderItemRepository.findByProductCode(productCode) == null) {
			return orderItemRepository.save(new OrderItem(productCode, productName, productQuantity));
		} else {
			return null;
		}
	}

	public OrderItem findByProductCode(Integer productCode) {
		log.info("inside findByProductCode for class :: OrderItemService");
		return orderItemRepository.findByProductCode(productCode);
	}

	public Iterable<OrderItem> lookup() {
		log.info("inside lookup for class :: OrderItemService");
		return orderItemRepository.findAll();
	}

	public long total() {
		return orderItemRepository.count();
	}
}

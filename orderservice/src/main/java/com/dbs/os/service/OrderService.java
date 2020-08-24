package com.dbs.os.service;

import static com.dbs.os.constants.Constants.ORDER_NOT_FOUND_FOR_CUSTOMER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.os.domain.Order;
import com.dbs.os.dto.OrderDto;
import com.dbs.os.exception.OrderNotFound;
import com.dbs.os.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jaypal sodha
 *
 */
@Service
@Slf4j
public class OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Order createOrderItem(OrderDto orderDto) {
		log.info("inside createOrderItem for class :: OrderService");
		return orderRepository.save(new Order(orderDto.getCustomerName(), orderDto.getOrderDate(),
				orderDto.getShippingAddress(), orderDto.getOrderItemList(), orderDto.getTotal()));
	}

	@Transactional(readOnly = true)
	public Order findByCustomerName(String customerName) {
		log.info("inside findByCustomerName for class :: OrderService");
		Order order = orderRepository.findByCustomerName(customerName);
		if (order == null) {
			throw new OrderNotFound(ORDER_NOT_FOUND_FOR_CUSTOMER + customerName);
		}
		return order;
	}

	public Iterable<Order> lookup() {
		log.info("inside lookup for class :: OrderService");
		return orderRepository.findAll();
	}

	public long total() {
		return orderRepository.count();
	}
}

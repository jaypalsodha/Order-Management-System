package com.dbs.os.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.os.domain.Order;
import com.dbs.os.domain.OrderItem;
import com.dbs.os.dto.OrderDto;
import com.dbs.os.repository.OrderRepository;

/**
 * @author jaypal
 *
 */
@Service
public class OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order createOrderItem(OrderDto orderDto) {
		return orderRepository.save(new Order(orderDto.getCustomerName(), orderDto.getOrderDate(),
				orderDto.getShippingAddress(), orderDto.getOrderItemList(), orderDto.getTotal()));
	}

	public Order findByCustomerName(String customerName) {
		Order order = orderRepository.findByCustomerName(customerName);
		if (order == null) {
			throw new NoSuchElementException("Order does not exist for Customer  " + customerName);
		}
		return order;
	}

	public Iterable<Order> lookup() {
		return orderRepository.findAll();
	}

	public long total() {
		return orderRepository.count();
	}
}

package com.dbs.os.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.os.domain.Order;
import com.dbs.os.dto.OrderDto;
import com.dbs.os.exception.OrderNotFound;
import com.dbs.os.repository.OrderRepository;
import static com.dbs.os.constants.Constants.*;

/**
 * @author jaypal sodha
 *
 */
@Service
public class OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public Order createOrderItem(OrderDto orderDto) {
		return orderRepository.save(new Order(orderDto.getCustomerName(), orderDto.getOrderDate(),
				orderDto.getShippingAddress(), orderDto.getOrderItemList(), orderDto.getTotal()));
	}

	@Transactional(readOnly = true)
	public Order findByCustomerName(String customerName) throws OrderNotFound {
		Order order = orderRepository.findByCustomerName(customerName);
		if (order == null) {
			throw new OrderNotFound(ORDER_NOT_FOUND_FOR_CUSTOMER + customerName);
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

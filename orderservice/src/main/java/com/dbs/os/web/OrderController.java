package com.dbs.os.web;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.os.client.OrderServiceFeignClient;
import com.dbs.os.domain.Order;
import com.dbs.os.domain.OrderItem;
import com.dbs.os.dto.OrderDto;
import com.dbs.os.exception.OrderNotFound;
import com.dbs.os.service.OrderService;

import lombok.NoArgsConstructor;

/**
 * 
 *
 * Created by jaypal sodha
 */
@RestController
@RequestMapping(path = "/orders")
@NoArgsConstructor
@EnableFeignClients
public class OrderController {

	OrderService orderService;
	OrderServiceFeignClient orderServiceFeignClient; 

	@Autowired
	public OrderController(OrderService orderService, OrderServiceFeignClient orderServiceFeignClient) {
		this.orderService = orderService;
		this.orderServiceFeignClient = orderServiceFeignClient;
	}

	@PostMapping(path = "/order/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Order createOrder(@RequestBody @Validated OrderDto orderDto) throws OrderNotFound {
		OrderItem oi = orderServiceFeignClient.findOrderItemByProductCode(1);
		System.out.println(oi);
		List<OrderItem> orderItemList = new ArrayList<>();
		orderDto.getProductCodeList().forEach(productCode ->{
			orderItemList.add(orderServiceFeignClient.findOrderItemByProductCode(productCode.getId()));
		});
		if(orderItemList.isEmpty()) {
			throw new OrderNotFound("Please select order item to proceed.");
		}
		return orderService.createOrderItem(orderDto);
	}

	@GetMapping(path = "/{customerName}")
	public OrderDto fetchOrderByCustomerName(@PathVariable(value = "customerName") String customerName) {
		Order order = orderService.findByCustomerName(customerName);
		return new OrderDto(order.getCustomerName(), order.getOrderDate(), order.getShippingAddress(),
				order.getOrderItemList(), order.getTotal());
	}

	@GetMapping
	public List<OrderDto> fetchAllOrderItem() {
		Iterable<Order> itrOrderItem = orderService.lookup();
		if (itrOrderItem == null) {
			throw new NoSuchElementException("Order item does not exist");
		}
		return StreamSupport.stream(itrOrderItem.spliterator(), false).map(orderItem -> toDto(orderItem))
				.collect(Collectors.toList());
	}

	private OrderDto toDto(Order order) {
		return new OrderDto(order.getCustomerName(), order.getOrderDate(), order.getShippingAddress(),
				order.getOrderItemList(), order.getTotal());
	}

	/**
	 * Exception handler if NoSuchElementException is thrown in this Controller
	 *
	 * @param ex
	 * @return Error message String.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex) {
		return ex.getMessage();
	}

}

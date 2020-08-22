package com.example.ec.web;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.ec.domain.Order;
import com.example.ec.domain.OrderItem;
import com.example.ec.repo.OrderRepository;

/**
 * 
 *
 * Created by jaypal sodha
 */
@RestController
@RequestMapping(path = "/orders")
public class OrderController {
  
	OrderRepository orderRepository; 
	
    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    protected OrderController() {

    }
    @PostMapping(path="/order/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody @Validated OrderDto orderDto) {
    	return  orderRepository.save(new Order(orderDto.getCustomerName(),orderDto.getOrderDate(),orderDto.getShippingAddress(),orderDto.getOrderItemList(),orderDto.getTotal()));
    }
    
    @GetMapping(path="/{customerName}")    
    public OrderDto fetchOrderByCustomerName(@PathVariable(value="customerName") String customerName) {
    	Order  order = orderRepository.findByCustomerName(customerName);
    	if (order == null) {
            throw new NoSuchElementException("Order does not exist for Customer  " + customerName);
        }        
    	return new OrderDto(order.getCustomerName(),order.getOrderDate(),order.getShippingAddress(),order.getOrderItemList(),order.getTotal());    	
    }
    @GetMapping    
    public List<OrderDto> fetchAllOrderItem() {
    	Iterable<Order> itrOrderItem = orderRepository.findAll();
    	if (itrOrderItem == null) {
            throw new NoSuchElementException("Order item does not exist");
        }
    	return StreamSupport.stream(itrOrderItem.spliterator(), false).map(orderItem -> toDto(orderItem)).collect(Collectors.toList());
    }
    
    private OrderDto toDto(Order order) {
    	return new OrderDto(order.getCustomerName(),order.getOrderDate(),order.getShippingAddress(),order.getOrderItemList(),order.getTotal());
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

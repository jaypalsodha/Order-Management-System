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

import com.example.ec.domain.OrderItem;
import com.example.ec.repo.OrderItemRepository;

/**
 * 
 *
 * Created by jaypal sodha
 */
@RestController
@RequestMapping(path = "/orderitem/order")
public class OrderItemController {
  
	OrderItemRepository orderItemRepository; 
	
    @Autowired
    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    protected OrderItemController() {

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItem createOrderItem(@RequestBody @Validated OrderItemDto orderItemDto) {
    	return  orderItemRepository.save(new OrderItem(orderItemDto.getProductCode(), orderItemDto.getProductName(), orderItemDto.getProductQuantity()));
    }
    
    @GetMapping(path="/{productCode}")    
    public OrderItemDto fetchOrderItemById(@PathVariable(value="productCode") int productCode) {
    	OrderItem  orderItem = orderItemRepository.findByProductCode(productCode);
    	if (orderItem == null) {
            throw new NoSuchElementException("Order item does not exist for product code:  " + productCode);
        }        
    	return new OrderItemDto(orderItem.getProductCode(),orderItem.getProductName(),orderItem.getProductQuantity());    	
    }
    @GetMapping    
    public List<OrderItemDto> fetchAllOrderItem() {
    	Iterable<OrderItem> itrOrderItem = orderItemRepository.findAll();
    	if (itrOrderItem == null) {
            throw new NoSuchElementException("Order item does not exist");
        }
    	return StreamSupport.stream(itrOrderItem.spliterator(), false).map(orderItem -> toDto(orderItem)).collect(Collectors.toList());
    }
    
    private OrderItemDto toDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProductCode(),orderItem.getProductName(),orderItem.getProductQuantity());
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

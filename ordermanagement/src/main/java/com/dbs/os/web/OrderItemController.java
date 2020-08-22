package com.dbs.os.web;

import java.util.List;
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

import com.dbs.os.domain.OrderItem;
import com.dbs.os.dto.OrderItemDto;
import com.dbs.os.exception.OrderNotFound;
import com.dbs.os.service.OrderItemService;

/**
 * @author jaypal
 *
 */
@RestController
@RequestMapping(path = "/orderitem/order")
public class OrderItemController {

	OrderItemService orderItemService;
	public static final String ORDER_ITEM_NOT_FOUND = "Order item does not exist";
	public static final String ORDER_ITEM_NOT_FOUND_FOR_PRODUCT_CODE = "Order item does not exist for product code";
	public static final String PATH_PRODUCT_CODE ="/{productCode}";

	@Autowired
	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	protected OrderItemController() {

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderItem createOrderItem(@RequestBody @Validated OrderItemDto orderItemDto) {
		return orderItemService.createOrderItem(orderItemDto.getProductCode(), orderItemDto.getProductName(),
				orderItemDto.getProductQuantity());
	}

	@GetMapping(path = PATH_PRODUCT_CODE)
	public OrderItemDto fetchOrderItemById(@PathVariable(value = "productCode") int productCode) throws OrderNotFound {
		OrderItem orderItem = orderItemService.findByProductCode(productCode);
		if (orderItem == null) {
			throw new OrderNotFound(ORDER_ITEM_NOT_FOUND_FOR_PRODUCT_CODE + productCode);
		}
		return new OrderItemDto(orderItem.getProductCode(), orderItem.getProductName(), orderItem.getProductQuantity());
	}

	@GetMapping
	public List<OrderItemDto> fetchAllOrderItem() throws OrderNotFound {
		Iterable<OrderItem> itrOrderItem = orderItemService.lookup();
		if (itrOrderItem == null) {
			throw new OrderNotFound(ORDER_ITEM_NOT_FOUND);
		}
		return StreamSupport.stream(itrOrderItem.spliterator(), false).map(OrderItemController::toDto)
				.collect(Collectors.toList());
	}

	/**
	 * @param orderItem
	 * @return
	 */
	private static OrderItemDto toDto(OrderItem orderItem) {
		return new OrderItemDto(orderItem.getProductCode(), orderItem.getProductName(), orderItem.getProductQuantity());
	}

	/**
	 * Exception handler if OrderNotFound is thrown in this Controller
	 *
	 * @param ex
	 * @return Error message String.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(OrderNotFound.class)
	public String return400(OrderNotFound ex) {
		return ex.getMessage();
	}

}

package com.dbs.os.web;

import static com.dbs.os.constants.Constants.ORDERITEM_ORDER_PATH;
import static com.dbs.os.constants.Constants.ORDER_ITEM_NOT_FOUND;
import static com.dbs.os.constants.Constants.ORDER_ITEM_NOT_FOUND_FOR_PRODUCT_CODE;
import static com.dbs.os.constants.Constants.PATH_PRODUCT_CODE;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.os.domain.OrderItem;
import com.dbs.os.dto.OrderItemDto;
import com.dbs.os.exception.OrderItemNotFound;
import com.dbs.os.service.OrderItemService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jaypal
 *
 */
@RestController
@RequestMapping(path = ORDERITEM_ORDER_PATH)
@NoArgsConstructor
@Slf4j
public class OrderItemController {

	OrderItemService orderItemService;

	@Autowired
	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderItem createOrderItem(@RequestBody @Validated OrderItemDto orderItemDto) {
		log.info("inside createOrderItem for class :: OrderItemController");
		return orderItemService.createOrderItem(orderItemDto.getProductCode(), orderItemDto.getProductName(),
				orderItemDto.getProductQuantity());
	}

	@GetMapping(path = PATH_PRODUCT_CODE)
	public OrderItemDto fetchOrderItemById(@PathVariable(value = "productCode") int productCode){
		log.info("inside fetchOrderItemById for class :: OrderItemController");
		OrderItem orderItem = orderItemService.findByProductCode(productCode);
		if (orderItem == null) {
			throw new OrderItemNotFound(ORDER_ITEM_NOT_FOUND_FOR_PRODUCT_CODE + productCode);
		}
		return new OrderItemDto(orderItem.getProductCode(), orderItem.getProductName(), orderItem.getProductQuantity());
	}

	@GetMapping
	public List<OrderItemDto> fetchAllOrderItem(){
		log.info("inside fetchAllOrderItem for class :: OrderItemController");
		Iterable<OrderItem> itrOrderItem = orderItemService.lookup();
		if (itrOrderItem == null) {
			throw new OrderItemNotFound(ORDER_ITEM_NOT_FOUND);
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
}

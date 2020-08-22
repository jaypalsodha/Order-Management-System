package com.dbs.os.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbs.os.domain.OrderItem;

/**
 * @author jaypal sodha
 *
 */
@FeignClient(name="stores",url="http://localhost:8080")
@Service
public interface OrderServiceFeignClient {
	@GetMapping(value = "/orderitem/order/{productCode}")
	public OrderItem findOrderItemByProductCode(
			@RequestParam(name = "productCode", required = true) Integer productCode);
}

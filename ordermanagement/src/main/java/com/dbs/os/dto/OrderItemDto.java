package com.dbs.os.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import static com.dbs.os.constants.Constants.*;

/**
 * 
 * created by jaypal sodha
 */
@Getter @Setter @NoArgsConstructor
public class OrderItemDto {

	@Min(value = 1, message = PRODUCT_QUANTITY_CANNOT_BE_LESS_THAN_ONE)
    private Integer productQuantity;

    @Min(value=1)
    @Max(value=512)
    @NotEmpty(message=PRODUCT_NAME_MUST_NOT_BE_EMPTY)
    private String productName;

    @Min(value = 1, message = PRODUCT_CODE_IS_INVALID)
    private Integer productCode;
         
    public OrderItemDto(Integer productQuantity, String productName, Integer productCode) {
		super();
		this.productQuantity = productQuantity;
		this.productName = productName;
		this.productCode = productCode;
	}
}

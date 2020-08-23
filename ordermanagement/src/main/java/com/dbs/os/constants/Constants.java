package com.dbs.os.constants;

public final class Constants {
	
	private Constants() {}
	public static final String ORDERS_PATH = "/orders";
	public static final String ORDER_CREATE_PATH = "/order/create";
	public static final String SELECT_ORDER_ITEM_TO_PROCEED = "Please select order item to proceed";
	public static final String ORDER_ITEM_NOT_FOUND = "Order item does not exist";
	public static final String ORDER_ITEM_NOT_FOUND_FOR_PRODUCT_CODE = "Order item does not exist for product code";
	public static final String PATH_PRODUCT_CODE ="/{productCode}";
	public static final String ORDERITEM_ORDER_PATH ="/orderitem/order";
	public static final String PRODUCT_QUANTITY_CANNOT_BE_LESS_THAN_ONE ="Product quantity cannot be less than 1";
	public static final String PRODUCT_NAME_MUST_NOT_BE_EMPTY ="Product name must not be empty";
	public static final String PRODUCT_CODE_IS_INVALID ="Product code is invalid";
	
}

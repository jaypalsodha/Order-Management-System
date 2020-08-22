package com.dbs.os.exception;

/**
 * @author jaypal sodha
 *
 */
public class OrderItemNotFoundException extends RuntimeException { 
	private static final long serialVersionUID = 4524870489625077207L;

	public OrderItemNotFoundException(String exceptionMessage) { 
        super(exceptionMessage); 
    } 
} 
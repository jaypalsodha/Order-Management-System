package com.dbs.os.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author jaypal
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderItemNotFound extends Exception { 
	private static final long serialVersionUID = 4524870489625077207L;

	public OrderItemNotFound(String exceptionMessage) { 
        super(exceptionMessage); 
    } 
} 
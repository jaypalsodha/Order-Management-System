package com.dbs.os.exception;

/**
 * @author jaypal
 *
 */
public class OrderNotFound extends Exception { 
	private static final long serialVersionUID = 4524870489625077207L;

	public OrderNotFound(String exceptionMessage) { 
        super(exceptionMessage); 
    } 
} 
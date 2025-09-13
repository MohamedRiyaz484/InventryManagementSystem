package com.project.IMS.customExceptions;

public class OutOfStockException extends RuntimeException {
	public OutOfStockException(String message) {
        super(message);
    }
}

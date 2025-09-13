package com.project.IMS.customExceptions;

public class StockExceededException extends RuntimeException{
	public StockExceededException(String message)
	{
		super(message);
	}
	
}

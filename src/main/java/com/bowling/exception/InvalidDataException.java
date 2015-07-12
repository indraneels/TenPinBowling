package com.bowling.exception;

/**
 * This Checked Exception class is used to throw any Invalid Data in the application. 
 * 
 * @author Indraneel Sanikommu
 *
 */
public class InvalidDataException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidDataException(){}
	
	public InvalidDataException(String message){
		super(message);
	}
	
	public InvalidDataException(Throwable  cause){
		super(cause);
	}
	
	public InvalidDataException(String message, Throwable  cause){
		super(message, cause);
	}

}

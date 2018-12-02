package com.sparknetworks.exception;


@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String message){
		super(message);
	}
	
	public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

	
	public ResourceNotFoundException(Throwable cause) {
	        super(cause);
	}
}

package com.sparknetworks.exception;

import java.io.IOException;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends IOException{
	
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

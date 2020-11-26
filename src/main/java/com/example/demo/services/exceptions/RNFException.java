package com.example.demo.services.exceptions;

//RNFE == ResourceNotFoundException
public class RNFException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RNFException(Object id) {
		super("Resource not found: Id: " + id);
	}

}

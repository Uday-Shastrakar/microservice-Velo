package com.department.exception;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public ResourceNotFoundException() {
		super("Reosurce not found on Server!");
	}
	
	
	public ResourceNotFoundException(String massage) {
		super(massage);
	}

}

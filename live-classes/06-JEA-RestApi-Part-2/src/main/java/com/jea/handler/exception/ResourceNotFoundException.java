package com.jea.handler.exception;

import jakarta.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException{
	
	private static final long serialVersionUID = -296707617546510001L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
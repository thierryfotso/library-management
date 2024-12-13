package com.application.library.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7553347569055927825L;

	public ObjectNotFoundException(final String message) {
		super(message);
	}
}

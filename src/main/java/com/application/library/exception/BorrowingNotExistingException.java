package com.application.library.exception;

public class BorrowingNotExistingException extends RuntimeException {

	private static final long serialVersionUID = -7912768371277320852L;

	public BorrowingNotExistingException() {
		super("book already borrowed");
	}
}

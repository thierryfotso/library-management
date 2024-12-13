package com.application.library.exception;

public class BookAlreadyBorrowedException extends RuntimeException {

	private static final long serialVersionUID = -7912768371277320852L;

	public BookAlreadyBorrowedException() {
		super("user did not borrowed this book");
	}
}
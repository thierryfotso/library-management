package com.application.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({ ObjectNotFoundException.class })
	public ResponseEntity<String> bookWasNotBorrowedExceptionHandler(final ObjectNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler({ BookAlreadyBorrowedException.class })
	public ResponseEntity<String> bookWasNotBorrowedExceptionHandler(final BookAlreadyBorrowedException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}

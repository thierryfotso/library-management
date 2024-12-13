package com.application.library.rest.controllers;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.library.entity.Book;
import com.application.library.entity.Borrowing;
import com.application.library.entity.Member;
import com.application.library.exception.BookAlreadyBorrowedException;
import com.application.library.exception.BorrowingNotExistingException;
import com.application.library.exception.ObjectNotFoundException;
import com.application.library.mappers.BookMapper;
import com.application.library.rest.dto.BookRequest;
import com.application.library.rest.dto.BorrowingRequest;
import com.application.library.service.BookService;
import com.application.library.service.BorrowingService;
import com.application.library.service.MemberService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private BookService bookService;

	@Autowired
	private BookMapper bookMapping;

	@Autowired
	private BorrowingService borrowingService;

	@PostMapping
	public ResponseEntity<BookRequest> createBook(@RequestBody final BookRequest bookRequest) {
		final Book bookEntity = bookService.save(bookMapping.convertToBook(bookRequest));
		return ResponseEntity.ok(bookMapping.convertToBookDTO(bookEntity));
	}

	@PutMapping
	public ResponseEntity<BookRequest> updateBook(@RequestBody final BookRequest bookRequest) {
		final Book bookEntity = bookService.save(bookMapping.convertToBook(bookRequest));
		return ResponseEntity.ok(bookMapping.convertToBookDTO(bookEntity));
	}

	@PostMapping(value = "/borrow")
	public void borrowBook(@RequestBody final BorrowingRequest borrowingRequest) {
		final Book bookToBorrow = bookService.get(borrowingRequest.getBookId());
		if (bookToBorrow == null) {
			throw new ObjectNotFoundException("book not found");
		}
		final Member borrowingMember = memberService.getMember(borrowingRequest.getMemberId());
		if (borrowingMember == null) {
			throw new ObjectNotFoundException("member not found");
		}
		final Borrowing oldBorrowing = borrowingService.getBorrowingByBookId(borrowingRequest.getBookId());
		if (oldBorrowing != null) {
			throw new BookAlreadyBorrowedException();
		}
		final Borrowing borrowing = new Borrowing();
		borrowing.setBorrowingDate(LocalTime.now());
		borrowing.setBook(bookToBorrow);
		borrowing.setMember(borrowingMember);
		borrowingService.save(borrowing);
	}

	@PutMapping(value = "/return/{bookId}")
	public void returnBook(final Long bookId) {
		final Book bookToBorrow = bookService.get(bookId);
		if (bookToBorrow == null) {
			throw new ObjectNotFoundException("book not found");
		}
		final Borrowing borrowing = borrowingService.getBorrowingByBookId(bookId);
		if (borrowing == null) {
			throw new BorrowingNotExistingException();
		}
		borrowingService.returnBook(bookId);
	}

	@GetMapping(value = "/borrowed/member/{idMember}")
	public ResponseEntity<List<BookRequest>> getBookBorrowed(@PathVariable("idMember") final Long id) {
		final Member member = memberService.getMember(id);
		if (member == null) {
			throw new ObjectNotFoundException("member not found");
		}
		final List<BookRequest> borrowedBooks = borrowingService.getBorrowedBooksByMember(id).stream()
				.map(Borrowing::getBook).map(bookMapping::convertToBookDTO).toList();
		return ResponseEntity.ok(borrowedBooks);
	}
}

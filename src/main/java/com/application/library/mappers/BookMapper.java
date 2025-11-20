package com.application.library.mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.application.library.entity.Book;
import com.application.library.entity.Borrowing;
import com.application.library.entity.Category;
import com.application.library.entity.Member;
import com.application.library.rest.dto.BookRequest;
import com.application.library.rest.dto.BorrowingRequest;

@Component
public class BookMapper {

	public Book convertToBook(final BookRequest bookDTO) {
		return Book.builder().id(bookDTO.getId()).authorName(bookDTO.getAuthorName())
				.category(Category.from(bookDTO.getCategory())).isbn(bookDTO.getIsbn()).title(bookDTO.getTitle())
				.build();
	}

	public List<BookRequest> toBookRequest(final List<Book> books) {
		return books.stream().map(this::convertToBookDTO).collect(Collectors.toList());
	}

	public BookRequest convertToBookDTO(final Book book) {
		return BookRequest.builder().id(book.getId()).title(book.getTitle()).isbn(book.getIsbn())
				.authorName(book.getAuthorName()).category(Objects.toString(book.getCategory())).build();
	}

	public Borrowing convertToBorrowingDTO(final BorrowingRequest borrowingDTO) {
		final Book book = new Book();
		book.setId(borrowingDTO.getBookId());

		final Member member = new Member();
		member.setId(borrowingDTO.getMemberId());
		return Borrowing.builder().book(book).member(member).build();
	}
}

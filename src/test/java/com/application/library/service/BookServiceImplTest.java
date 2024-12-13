package com.application.library.service;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.application.library.entity.Book;
import com.application.library.repository.BookRepository;

public class BookServiceImplTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookServiceImpl bookService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSave() {
		final Book expectedBook = Book.builder().id(2L).authorName("author Name").build();
		doReturn(expectedBook).when(bookRepository).save(expectedBook);
		final Book actualBook = bookService.save(expectedBook);
		Assertions.assertThat(actualBook.getId()).isEqualTo(expectedBook.getId());
		Assertions.assertThat(actualBook.getAuthorName()).isEqualTo(expectedBook.getAuthorName());
	}

	@Test
	public void testGet() {
		final long bookId = 2L;
		final Book expectedBook = Book.builder().id(bookId).authorName("author Name").build();
		doReturn(Optional.ofNullable(expectedBook)).when(bookRepository).findById(ArgumentMatchers.anyLong());
		final Book actualBook = bookService.get(bookId);
		Assertions.assertThat(actualBook.getId()).isEqualTo(expectedBook.getId());
		Assertions.assertThat(actualBook.getAuthorName()).isEqualTo(expectedBook.getAuthorName());
	}

}

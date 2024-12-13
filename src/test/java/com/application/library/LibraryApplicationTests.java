package com.application.library;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.library.entity.Book;
import com.application.library.repository.BookRepository;

@SpringBootTest
class LibraryApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Test
	void testCreateBook() {
		final Book book = new Book();
		book.setTitle("Book1");
		book.setIsbn(325147L);
		book.setPublicationDate(LocalDate.now());
		bookRepository.save(book);
	}

}

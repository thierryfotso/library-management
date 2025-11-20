package com.application.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.library.entity.Book;
import com.application.library.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(final Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void delete(final Long idBook) {
		bookRepository.deleteById(idBook);
	}

	@Override
	public Book get(final Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

}

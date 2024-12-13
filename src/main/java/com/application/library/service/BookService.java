package com.application.library.service;

import com.application.library.entity.Book;

public interface BookService {

	Book get(Long id);

	Book save(Book book);

	void delete(Long id);
}

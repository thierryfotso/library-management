package com.application.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByOrderByTitleAsc();

}

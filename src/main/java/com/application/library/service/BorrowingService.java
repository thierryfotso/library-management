package com.application.library.service;

import java.util.List;

import com.application.library.entity.Borrowing;

public interface BorrowingService {

	void save(Borrowing borrowing);

	void delete(Long id);

	void returnBook(final Long bookId);

	List<Borrowing> getBorrowedBooksByMember(Long memberId);

	Borrowing getBorrowingByBookId(Long bookId);
}

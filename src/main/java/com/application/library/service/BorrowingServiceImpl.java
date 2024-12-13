package com.application.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.library.entity.Borrowing;
import com.application.library.repository.BorrowingRepository;

@Service
public class BorrowingServiceImpl implements BorrowingService {

	@Autowired
	private BorrowingRepository borrowingRepository;

	@Override
	public void save(final Borrowing borrowing) {
		borrowingRepository.save(borrowing);
	}

	@Override
	public void delete(final Long borrowingId) {
		borrowingRepository.deleteById(borrowingId);
	}

	@Override
	public List<Borrowing> getBorrowedBooksByMember(final Long memberId) {
		return borrowingRepository.findByMemberId(memberId);
	}

	@Override
	public void returnBook(final Long bookId) {
		borrowingRepository.deleteByMemberIdBookId(bookId);
	}

	@Override
	public Borrowing getBorrowingByBookId(final Long bookId) {
		return borrowingRepository.findByBookId(bookId);
	}

}

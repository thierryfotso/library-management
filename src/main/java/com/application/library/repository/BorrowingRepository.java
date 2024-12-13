package com.application.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.application.library.entity.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

	List<Borrowing> findByMemberId(Long memberId);

	Borrowing findByBookId(Long bookId);

	@Transactional
	@Modifying
	@Query("delete from Borrowing b where b.book.id =?1")
	void deleteByMemberIdBookId(Long bookId);

}

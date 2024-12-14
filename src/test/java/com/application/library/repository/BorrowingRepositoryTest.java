package com.application.library.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import com.application.library.entity.Book;
import com.application.library.entity.Borrowing;
import com.application.library.entity.Member;

@DirtiesContext
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class BorrowingRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BorrowingRepository borrowingRepository;

	@Test
	public void testFindByMemberId() {
		final Book book = bookRepository.findById(1L).orElse(null);
		final Member member = memberRepository.findById(2L).orElse(null);
		final Borrowing borrowing = Borrowing.builder().book(book).member(member).build();
		borrowingRepository.save(borrowing);

		final List<Borrowing> borrowings = borrowingRepository.findByMemberId(2L);
		assertThat(borrowings).hasSize(1);
		assertThat(borrowings.get(0).getMember().getId()).isEqualTo(member.getId());
		assertThat(borrowings.get(0).getBook().getId()).isEqualTo(book.getId());
	}
}

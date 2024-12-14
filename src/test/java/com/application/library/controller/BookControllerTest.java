package com.application.library.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.application.library.entity.Book;
import com.application.library.entity.Category;
import com.application.library.mappers.BookMapper;
import com.application.library.rest.controllers.BookController;
import com.application.library.rest.dto.BookRequest;
import com.application.library.service.BookServiceImpl;
import com.application.library.service.BorrowingService;
import com.application.library.service.MemberServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookController.class)
@Import(BookMapper.class)
@MockBeans({ @MockBean(BookServiceImpl.class), @MockBean(MemberServiceImpl.class), })
public class BookControllerTest {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BorrowingService borrowingService;

	@MockBean
	private BookServiceImpl bookService;

	@Test
	public void testCreateBook() throws Exception {
		final BookRequest bookRequest = BookRequest.builder().authorName("author")
				.category(Category.ARGUMENTATIVE.name()).isbn(124587L).title("Title Book").build();

		final Book expectedBook = Book.builder().id(1L).authorName("autthor").title("book title").build();
		doReturn(expectedBook).when(bookService).save(Mockito.any(Book.class));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/book").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(bookRequest))).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(expectedBook.getId()))
				.andExpect(jsonPath("$.title").value(expectedBook.getTitle()));
	}

	private String toJson(final Object object) throws JsonProcessingException {
		return objectMapper.writeValueAsString(object);
	}
}

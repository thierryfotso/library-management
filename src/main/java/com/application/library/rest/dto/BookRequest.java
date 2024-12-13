package com.application.library.rest.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

	private Long id;

	private String title;

	private Long isbn;

	private Date publicationDate;

	private String authorName;

	private String category;

}

package com.application.library.rest.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

	private Long id;

	private String name;

	private String address;

	private String email;

	private String password;

	private String phoneNumber;

	private LocalTime registrationDate;

}

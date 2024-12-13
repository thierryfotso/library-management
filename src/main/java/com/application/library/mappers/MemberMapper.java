package com.application.library.mappers;

import org.springframework.stereotype.Component;

import com.application.library.entity.Member;
import com.application.library.rest.dto.MemberRequest;

@Component
public class MemberMapper {

	public Member convertToMember(final MemberRequest memberDTO) {
		return Member.builder().name(memberDTO.getName()).address(memberDTO.getAddress()).email(memberDTO.getEmail())
				.password(memberDTO.getPassword()).phoneNumber(memberDTO.getPhoneNumber()).build();
	}

	public MemberRequest convertToMemberDTO(final Member member) {
		return MemberRequest.builder().id(member.getId()).name(member.getName()).address(member.getAddress())
				.email(member.getEmail()).password(member.getPassword()).phoneNumber(member.getPhoneNumber()).build();
	}
}

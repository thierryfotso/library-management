package com.application.library.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.library.entity.Member;
import com.application.library.mappers.MemberMapper;
import com.application.library.rest.dto.MemberRequest;
import com.application.library.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberMapper memberMapper;

	@PostMapping
	public ResponseEntity<MemberRequest> createMember(@RequestBody final MemberRequest memberDTO) {
		final Member member = memberService.save(memberMapper.convertToMember(memberDTO));
		return ResponseEntity.ok(memberMapper.convertToMemberDTO(member));
	}

	@GetMapping(value = "/{idMember}")
	public ResponseEntity<MemberRequest> getMember(@PathVariable("idMember") final Long id) {
		final MemberRequest member = memberMapper.convertToMemberDTO(memberService.getMember(id));
		return ResponseEntity.ok(member);
	}
}

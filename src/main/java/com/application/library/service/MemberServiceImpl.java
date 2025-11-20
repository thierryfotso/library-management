package com.application.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.library.entity.Member;
import com.application.library.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public Member save(final Member member) {
		member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
		return memberRepository.save(member);
	}

	@Override
	public Member getMember(final Long memberId) {
		return memberRepository.findById(memberId).orElse(null);
	}

	@Override
	public Member getMemberByEmail(final String email) {
		return memberRepository.findByEmail(email).orElse(null);
	}

}

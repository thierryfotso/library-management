package com.application.library.service;

import com.application.library.entity.Member;

public interface MemberService {

	Member save(final Member member);

	Member getMember(Long memberId);
}

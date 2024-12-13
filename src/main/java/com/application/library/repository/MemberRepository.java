package com.application.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.library.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}

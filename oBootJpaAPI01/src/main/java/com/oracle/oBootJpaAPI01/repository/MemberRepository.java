package com.oracle.oBootJpaAPI01.repository;

import java.util.List;

import com.oracle.oBootJpaAPI01.domain.Member;



public interface MemberRepository {
	Long 			save(Member member);
	List<Member>    findAll();
}

package com.oracle.bootJpaAPI01.repository;

import java.util.List;
import java.util.Optional;

import com.oracle.bootJpaAPI01.domain.Member;


public interface MemberRepository {
	Long              save(Member member);
	Member            findByMember(Long id);
	List<Member>      findByNames(String name);
	List<Member>      findAll();
	int               updateByMember(Member member);

}

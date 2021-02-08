package com.oracle.oBootJpaAPI01.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootJpaAPI01.domain.Member;
import com.oracle.oBootJpaAPI01.domain.Team;


@Repository
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;
    
    public JpaMemberRepository(EntityManager em) {
    	this.em = em;
    }

	@Override
	public Long save(Member member) {
		//회원 저장
		em.persist(member);
		return member.getId();
	}

	@Override
	public List<Member> findAll() {
		// SQL No, JPA 문법
		List<Member> memberList = em.createQuery("select m from Member m",Member.class)
								  .getResultList();
		return memberList;
	}

	
	
	




}

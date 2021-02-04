package com.oracle.oBootJpa01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.oracle.oBootJpa01.domain.Member;

public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}

	@Override
	public List<Member> findByNames(String name) {
		String pname = name + '%';
		System.out.println("JpaMemberRepository findByNames pname->"+pname);
		List<Member> memberList = em.createQuery("select m from Member m where name LIKE :name", Member.class)
				.setParameter("name", pname)
				.getResultList();
		System.out.println("JpaMemberRepository findByNames memberList.size()->"+memberList.size());
		return memberList;
	}

}

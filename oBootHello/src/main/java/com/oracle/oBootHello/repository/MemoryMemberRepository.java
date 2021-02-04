package com.oracle.oBootHello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootHello.domain.Member1;

@Repository
public class MemoryMemberRepository implements MemberRepository {
	private static Map<Long, Member1> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member1 save(Member1 member1) {
		member1.setId(++sequence);
		store.put(member1.getId(), member1);
		return member1;
	}
	@Override
	public Member1 findById(Long id) {
		Member1 member3 = new Member1();
		member3 = store.get(id);
		return member3;
	}
	@Override
	public List<Member1> findAll() {
		System.out.println("MemoryMemberRepository findAll start...");
		List<Member1> listMember = new ArrayList<>(store.values());
		System.out.println("MemoryMemberRepository findAll slistMember.size()->"+listMember.size());
		return listMember;
	}



}

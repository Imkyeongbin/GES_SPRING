package com.oracle.oBootJpa01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa01.domain.Member;
import com.oracle.oBootJpa01.repository.MemberRepository;

// 메서드가 정상 종료되면 트랜잭션을 커밋, 만약 런타임 예외가 발생하면 롤백.
// JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행
//@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	
	//@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	// 회원가입
	public Long join(Member member) {
		System.out.println("MemberService join member.getId()->"+member.getId());
		memberRepository.save(member);
		return member.getId();
	}
	
	// 전체회원 조회
	public List<Member> getListAllMember(){
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size()->"+listMember.size());
		return listMember;
	}
	
	// 선택회원 리스트
	public List<Member> getListSearchMember(String searchName) {
		System.out.println("MemberService getListSearchMember Start...");
		// String pSearchName = searchName + '%'; 
		System.out.println("MemberService getListSearchMember searchName->"+searchName);
		List<Member> listMember = memberRepository.findByNames(searchName);
		System.out.println("MemberService getListSearchMember listMember.size()->"+listMember.size());
		return listMember;
	}
}

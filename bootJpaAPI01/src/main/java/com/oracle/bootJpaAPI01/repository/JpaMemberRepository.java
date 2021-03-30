package com.oracle.bootJpaAPI01.repository;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.bootJpaAPI01.domain.Member;

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

	// Optional 객체를 사용하면 예상치 못한 NullPointerException 예외를 제공되는 메소드로 간단히 회피.
	// 즉, 복잡한 조건문 없이도 널(null) 값으로 인해 발생하는 예외를 처리
	@Override
	public Member findByMember(Long id) {
		Member member = em.find(Member.class, id);
		return member;
	}
//  stream
//	스트림은 자바8부터 추가된 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자
//	Iterator와 비슷한 역할을 하지만 람다식으로 요소 처리 코드를 제공하여 코드가 좀 더 간결하게 할 수 있다는 점과  
//	내부 반복자를 사용하므로 병렬처리가 쉽다는 점에서 차이점
	@Override
	public List<Member> findByNames(String name) {
		String pname = name + '%';
		System.out.println("JpaMemberRepository findByNames name->"+name);

		List<Member> memberList = em.createQuery("select m from Member m where name Like :name", Member.class)
				                 .setParameter("name",pname)
				                 .getResultList();
		System.out.println("JpaMemberRepository memberList.size()->"+memberList.size());
		return memberList;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class)
				                  .getResultList();

		return memberList;
	}


	
	@Override
	public int updateByMember(Member member) {
		int result = 0;
	       Member member3 = em.find(Member.class, member.getId());
	     //  member3.ifPresent(object);
	       if( member3 != null) {
	  		 //회원 저장
		     member3.setName(member.getName()); // 저장
	  		 em.persist(member3);	
	  		 System.out.println("JpaMemberRepository updateByMember member.getName()->"+member.getName());
	  		 result = 1;  
	       } else {
	    	 result = 0;
	  		 System.out.println("JpaMemberRepository updateByMember No Exist..");
	       }
		 return result;
	}

}

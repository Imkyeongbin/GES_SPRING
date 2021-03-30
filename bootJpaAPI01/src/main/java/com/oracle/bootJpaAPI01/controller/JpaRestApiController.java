package com.oracle.bootJpaAPI01.controller;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.bootJpaAPI01.domain.Member;
import com.oracle.bootJpaAPI01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JpaRestApiController {
	// private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JpaRestApiController.class);

	 private final MemberService memberService;

	 /**
	  * 등록 V1: 요청 값으로 Member 엔티티를 직접 받는다.
	  * 문제점
	  * - 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
	  * - 엔티티에 API 검증을 위한 로직이 들어간다. (@NotEmpty 등등)
	  * - 실무에서는 회원 엔티티를 위한 API가 다양하게 만들어지는데, 한 엔티티에 각각의 API를 위한 모든 요청 요구사항을 담기는 어렵다.
	  * - 엔티티가 변경되면 API 스펙이 변한다.
	  * 결론
	  * - API 요청 스펙에 맞추어 별도의 DTO를 파라미터로 받는다.
	  *  JSON Data를 (@RequestBody) Member member에 넣어준다
	  */
	  @PostMapping("/api/v1/memberSave")
	  public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member)	 {
		 System.out.println("JpaRestApiController /api/v1/memberSave member.getId()->"+member.getId());
		 //logger.info("JpaRestApiController member.getId()-> {}.", member.getId());
		 Long id = memberService.join(member);
		 return new CreateMemberResponse(id);
	  }
	  @Data
	  class CreateMemberResponse {
		  private Long id;
		  public CreateMemberResponse(Long id) {
			  this.id = id;
		  }
	  }
	  
	  /**
	   * 등록 V2: 요청 값으로 Member 엔티티 대신에 별도의 DTO를 받는다.
	   */
	   @PostMapping("/api/v2/memberSave")
	   public CreateMemberResponse saveMemberV2(@RequestBody @Valid	  CreateMemberRequest request) {
		   System.out.println("JpaRestApiController /api/v2/memberSave request.getName()->"+request.getName());
		   Member member = new Member();
		   member.setName(request.getName());
		   Long id = memberService.join(member);
		   return new CreateMemberResponse(id);
	   }
		  @Data
		  static class CreateMemberRequest {
			  @NotEmpty
			  private String name;
		  }

	  /**
	   * 수정 API
	   * PUT 방식을사용했는데, PUT은 전체 업데이트를 할 때 사용하는 것이 맞다. 
	   * 부분 업데이트를 하려면 PATCH를 사용하거나 POST를 사용하는 것이 REST 스타일에 맞다
	   */
	  @PutMapping("/api/v2/members/{id}")
	  public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,  @RequestBody @Valid UpdateMemberRequest request) {
		   memberService.update(id, request.getName());
		   Member findMember = memberService.findByMember(id);
		   return new UpdateMemberResponse(findMember.getId(), findMember.getName());
	  }
	  @Data
	  static class UpdateMemberRequest {
		  private String name;
	  }
	  @Data
	  @AllArgsConstructor
	  class UpdateMemberResponse {
		   private Long id;
		   private String name;
	  } 
	 
	  /**
	   * 조회 V1: 응답 값으로 엔티티를 직접 외부에 노출한다.
	   * 문제점
	   * - 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
	   * - 기본적으로 엔티티의 모든 값이 노출된다.
	   * - 응답 스펙을 맞추기 위해 로직이 추가된다. (@JsonIgnore, 별도의 뷰 로직 등등)
	   * - 실무에서는 같은 엔티티에 대해 API가 용도에 따라 다양하게 만들어지는데, 한 엔티티에 각각의
	        API를 위한 프레젠테이션 응답 로직을 담기는 어렵다.
	   * - 엔티티가 변경되면 API 스펙이 변한다.
	   * - 추가로 컬렉션을 직접 반환하면 항후 API 스펙을 변경하기 어렵다.(별도의 Result 클래스  생성으로 해결)
	   * 결론
	   * - API 응답 스펙에 맞추어 별도의 DTO를 반환한다.
	   */
	   // 조회 V1: 안 좋은 버전, 모든 엔티티가 노출, @JsonIgnore -> 이건 정말 최악, api가 이거  하나인가! 화면에 종속적이지 마라!
	   @GetMapping("/api/v1/members")
	   		public List<Member> membersV1() {
		   return memberService.getListAllMember();
	   }	 
	 
	   /**
	    * 조회 V2: 응답 값으로 엔티티가 아닌 별도의 DTO를 반환한다.
	    */
	    @GetMapping("/api/v2/members")
	    public Result membersV2() {
	    List<Member> findMembers = memberService.getListAllMember();
		    // 엔티티 -> DTO 변환
	        // 자바 8 이전에는 배열 또는 컬렉션 인스턴스를 다루는 방법은 for 또는 foreach 문을 돌면서 요소 하나씩을 꺼내서 다루는 방법
	        // 자바 8에서 추가한 스트림(Streams)은 람다를 활용할 수 있는 기술 중 하나
	        // stream.collect() Data 중간처리후 마지막에 원하는 Data로 변환 
		    List<MemberDto> collect = findMembers.stream()
								    .map(m -> new MemberDto(m.getName())) // 맵(map)은 스트림 내 요소들을 하나씩 특정 값으로 변환
								    .collect(Collectors.toList());        // Collect는 Stream의 데이터를 변형 등의 처리를 하고 원하는 자료형으로 변환

//		    List<MemberDto> collect = null;
//		    for (Member findMember : findMembers ) {
//		    	String vName = "";
//		    	vName = findMember.getName();
//			    MemberDto memberDto = new MemberDto(vName);
//		    	collect.add(memberDto);
//		    }
		    
		    return new Result(collect.size() , collect);
		//    return new Result(collect);
	    }
	    // 이렇게 하는 이유는 Entity보안 
	    // 여러곳에서 개발하기 때문에 각각의 것을 모두 맞추어 주기 어려움 
	    // T는 인스턴스를 생성할 때 구체적인 타입으로 변경 된다. --> https://aristatait.tistory.com/68
	    // 이와 같이 제네릭은 클래스를 설계할 때 구체적인 타입을 명시하지 않고, 타입 파라미터로 대체했다가 실제 클래스가 사용될 때 구체적인 타입을 지정함으로써 타입 변환을 최소화
	    @Data
	    @AllArgsConstructor
	    class Result<T> {
	    	private int totCnt;
		    private T data;
		    
	    }
	    
	    @Data
	    @AllArgsConstructor
	    class MemberDto {
		    private String name;
	    } 
	 
	 
	 
	 

}
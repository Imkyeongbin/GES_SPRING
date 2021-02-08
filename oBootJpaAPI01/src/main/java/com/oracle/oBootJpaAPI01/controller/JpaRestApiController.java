package com.oracle.oBootJpaAPI01.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootJpaAPI01.domain.Member;
import com.oracle.oBootJpaAPI01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

// Controller + ResponseBody
// 1. API
// 2. Ajax
@RestController
@RequiredArgsConstructor
public class JpaRestApiController {
	private final MemberService memberService;
	
	
	
	@PostMapping("/restApi/v1/memberSave")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
		System.out.println("JpaRestApiController /api/v1/memberSave member.getId()->"+member.getId());
		//logger.info("JpaRestApiController member.getId()-> {}.",member.getId());
		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
	@Data
	class CreateMemberResponse{
		private Long id;
		public CreateMemberResponse(Long id) {
			this.id = id;
		}
	}
	
	@PostMapping("/restApi/v2/memberSave")
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
		System.out.println("JpaRestApiController /api/v2/memberSave member.getName()->"+request.getName());
		Member member = new Member();
		member.setName(request.getName());
		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
	@Data
	static class CreateMemberRequest{
		@NotEmpty
		private String name;
		
	}
	
	// Bad API
	@GetMapping("/restApi/v1/members")
	public List<Member> membersV1(){
		return memberService.getListAllMember();
	}
	
	// Good API
	@GetMapping("/restApi/v2/members")
	public Result membersV2() {
		List<Member> findMembers = memberService.getListAllMember();
		
		// 자바 8에서 추가한 스트림(Stream)은 람다를 활용할 수 있는 기술 중 하나
		List<MemberRtnDto> memberCollect = findMembers.stream()
										.map(m -> new MemberRtnDto(m.getName()))
										.collect(Collectors.toList());
		return new Result(memberCollect.size(), memberCollect);
	}
	
	// 1. Entity 보안
	// 2. 유연성 --> Entity가 API에 의존적 X, 원하는 Data 생성, 전달
	// T는 인스턴스를 생성할 때 구체적인 타입으로 변경
	@Data
	@AllArgsConstructor
	class Result<T>{
		private int totCount;
		private T data;
	}
	
	@Data
	@AllArgsConstructor
	class MemberRtnDto{
		private String name;
	}
}

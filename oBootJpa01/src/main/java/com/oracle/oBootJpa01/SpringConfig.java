package com.oracle.oBootJpa01;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootJpa01.repository.JpaMemberRepository;
import com.oracle.oBootJpa01.repository.MemberRepository;
import com.oracle.oBootJpa01.service.MemberService;

@Configuration
public class SpringConfig {
	private final DataSource dataSource;
	private final EntityManager em;
	
	public SpringConfig(
			DataSource dataSource,
			EntityManager em)
	{
		this.dataSource = dataSource;
		this.em = em;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new JpaMemberRepository(em);
	}
	
}

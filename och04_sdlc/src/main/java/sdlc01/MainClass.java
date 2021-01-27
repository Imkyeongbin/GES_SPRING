package sdlc01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		System.out.println("1 ctx.load  Before");
		ctx.load("classpath:applicationCTX01.xml");
		System.out.println("2 ctx.load  After");
		// 실제 Bean 생성
		ctx.refresh();
		System.out.println("3 ctx.refresh  After");
		// 객체 종료(컨테이너 종료 -> 다른 용어로 빈 팩토리(Bean Factory), 애플리케이션 컨텍스트(Application Context))
		// Spring-IOC-컨테이너 :어떠한 객체의 명세서를 작성하고, 스프링 라이브러리는 해당 명세대로 객체를 생성.
		// 생성된 객체(그리고 디펜던시)들을 보관하는 공간을 의미
		ctx.close();
		System.out.println("4 ctx.close  After");
	}
}

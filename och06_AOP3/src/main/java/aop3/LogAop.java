package aop3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
//	@Pointcut("execution(public void get*(..))")	// public void인 모든 get메소드
//	@Pointcut("execution(*  aop4.*.*())")	        // aop4 패키지에 파라미터가 없는 모든 메소드
//	@Pointcut("execution(* aop4..*.*())")	        // aop4 패키지 & aop4 하위 패키지에 파라미터가 없는 모든 메소드
//	@Pointcut("execution(* aop4.Worker.*())")	    // aop4.Worker 안의 모든 메소드

//	@Pointcut("within(aop4.*)")	                //aop4 패키지 안에 있는 모든 메소드
//	@Pointcut("within(aop4..*)")                //aop4 패키지 및 하위 패키지 안에 있는 모든 메소드
//	@Pointcut("within(aop4.Worker)")            //aop4.Worker 모든 메소드

	
	@Pointcut("within(aop3.*)")
	private void pointcutMethod() {
		
	}
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable{
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println( signatureStr + " is start.");
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		}finally {
			long et = System.currentTimeMillis();
			System.out.println( signatureStr + " is finished.");
			System.out.println( signatureStr + " 경과시간 : "+ (et - st));
		}
	}
	
	@Before("within(aop3.S*)")
	public void beforAdvice() {
		System.out.println("beforAdvice()");
	}
	@After("within(aop3.*)")
	public void afterAdvice() {
		System.out.println("@AfterAdvice()");
	}
}

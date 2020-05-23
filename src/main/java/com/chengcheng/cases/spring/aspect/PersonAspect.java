package com.chengcheng.cases.spring.aspect;

import io.swagger.annotations.Api;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Api("切面测试类")
@Component
@Aspect
public class PersonAspect {

	/*@ApiOperation("开会之前--找个位置坐下")
    @Before("execution(* com.chengcheng.service.impl.HelloServiceImpl.conference(..))")
    public void takeSeats() {
        System.out.println("找位置坐");
    }

	@ApiOperation("开会之前--手机调成静音")
    @Before("execution(* com.chengcheng.service.impl.HelloServiceImpl.conference(..))")
    public void silenceCellPhones() {
        System.out.println("手机调成静音");
    }

	@ApiOperation("开会之后--写会议总结报告")
    @After("execution(* com.chengcheng.service.impl.HelloServiceImpl.conference(..))")
    public void summary() {
        System.out.println("写会议总结报告");
    }*/

	/*
	 * =========================================================================
	 * 从上面的执行代码可以看出切点execution表达式内容都是一样，
	 * 我们可以通过@Pointcut进行优化。
	 * =========================================================================
	 */

	/*
	 * 通过注解@Pointcut定义切点，conference()只是一个标识，无所谓是什么，
	 * 方法中内容本身也是空的，使用该切点的地方直接通过标识conference()引用切点表达式。
	 */
	/*@Pointcut("execution(* com.chengcheng.service.impl.HelloServiceImpl.conference(..))")
	public void conference() {}

	@ApiOperation("开会之前--找个位置坐下")
	@Before("conference()")
	public void takeSeats() {
		System.out.println("找位置坐");
	}

	@ApiOperation("开会之前--手机调成静音")
	@Before("conference()")
	public void silenceCellPhones() {
		System.out.println("手机调成静音");
	}

	@ApiOperation("开会之后--写会议总结报告")
	@After("conference()")
	public void summary() {
		System.out.println("写会议总结报告");
	}*/

	/**
	 * **************************************************************************
	 * 通过注解@Pointcut定义切点，conference()只是一个标识，无所谓是什么，
	 * 方法中内容本身也是空的，使用该切点的地方直接通过标识conference()引用切点表达式。
	 * ""execution(* com.chengcheng.service.impl.HelloServiceImpl.conference(..))"
	 * execution: 在方法执行时触发
	 * *: 返回任意类型
	 * com.chengcheng.cases.spring.aspect.ConferenceService: 方法所属的类即全名
	 * conference: 方法
	 * (..): 使用任意参数
	 * **************************************************************************
	 */

	@Pointcut("execution(* com.chengcheng.service.impl.HelloServiceImpl.conference(..))")
	public void conference() {}

	@Around("conference()")
	public void testAround(ProceedingJoinPoint jp) {
		try {
			System.out.println("开会之前--找个位置坐下");
			System.out.println("开会之前--手机调成静音");
			jp.proceed();
			System.out.println("开会之后--写会议总结报告");
		} catch (Throwable e) {
			System.out.println("出现异常.....");
		}
	}

}

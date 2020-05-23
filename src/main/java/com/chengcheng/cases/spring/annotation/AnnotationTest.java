package com.chengcheng.cases.spring.annotation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.lang.reflect.Method;

@Api("注解测试类")
public class AnnotationTest {

	@ApiOperation("对Annotation的测试")
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
		// 提取到被注解的方法Method，这里用到了反射的知识
		Method method = Class.forName("com.chengcheng.cases.spring.annotation.OneClass").getDeclaredMethod("oneMethod");
		// 从Method方法中通过方法getAnnotation获得我们设置的注解
		Annotation oneAnnotation = method.getAnnotation(Annotation.class);

		// 得到注解的俩参数
		System.out.println(oneAnnotation.parameter1());
		System.out.println(oneAnnotation.parameter2());
	}

}

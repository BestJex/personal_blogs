package com.chengcheng.cases.spring.annotation;

import io.swagger.annotations.Api;

@Api("使用自定义注解类")
public class OneClass {
	@Annotation(parameter1="YES", parameter2=10000)
	public void oneMethod () {
	}
}

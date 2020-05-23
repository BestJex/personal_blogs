package com.chengcheng.cases.current.single;

import io.swagger.annotations.Api;

@Api("饿汉式 - 枚举")
public class SingleEHan {
	private SingleEHan() {}
	private static SingleEHan singleEHan = new SingleEHan();
}

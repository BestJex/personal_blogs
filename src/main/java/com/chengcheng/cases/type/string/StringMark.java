package com.chengcheng.cases.type.string;

import io.swagger.annotations.Api;

@Api("演示字符串的单引号和双引号的使用场景")
public class StringMark {

	// 反例
	void method(String s) {
		String string = s + "d";
		string = "abc" + "d";
	}

	// 正例 - 将一个字符的字符串替换成 ''
	void method02(String s) {
		String string = s + 'd';
		string = "abc" + 'd';
	}

}

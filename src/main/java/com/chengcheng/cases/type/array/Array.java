package com.chengcheng.cases.type.array;

import io.swagger.annotations.Api;

@Api("可变数组案例")
public class Array {

	private Array() {
		test();
		test("a", "b");
		test(new String[]{"aaa", "bbb"});
		test("ccc");
	}

	private void test() {
		System.out.println("test");
	}

	private void test(String... strings) {
		for (String str : strings) {
			System.out.print(str + ", ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		new Array();
	}

}
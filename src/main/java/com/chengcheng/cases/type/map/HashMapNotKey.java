package com.chengcheng.cases.type.map;

import io.swagger.annotations.Api;

import java.util.HashMap;

@Api("演示HashMap获取不到Key的问题")
public class HashMapNotKey {

	public static void main(String[] args) {
		HashMap<String, Object> result = new HashMap<>();
		result.put("0", "10000");

		// 此For循环中存在取不到Key的问题
		for (int i = 0; i < result.size(); i ++) {
			System.out.println(result.get("0"));
			System.out.println(result.get(0));
			System.out.println(result.get(i));

			String str = "\"" + i + "\"";
			System.out.println(str);
			System.out.println(result.get(str));
		}

		System.out.println("---------------------");

		/*
		 *  解决:
		 * 1. 将key换成Integer
		 * 2. 使用String.valueOf()
		 */
		for (int i= 0; i < result.size(); i ++) {
			System.out.println(result.get(String.valueOf(i)));
		}

	}

}

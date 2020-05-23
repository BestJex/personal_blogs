package com.chengcheng.cases.type.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  演示: LinkedHashMap
 *  描述: LinkedHashMap在于存储数据你想保持进入的顺序与被取出的顺序一致的话, 有限考虑LinkedHashMap
 *  HashMap的Key只允许为一条为空, value可以允许为多条为空, 键唯一, 但值可以多个.
 */
public class LinkedHashMapCase {

	public static void main(String[] args) {

		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("a3", "aa");
		map.put("a2", "bb");
		map.put("b1", "cc");
		for (Iterator iterator = map.values().iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			System.out.println(name);
		}

		System.out.println("=================================");

		Map<String, String> map02 = new HashMap<String, String>();
		map02.put("a3", "aa");
		map02.put("a2", "bb");
		map02.put("b1", "cc");
		for (Iterator iterator = map02.values().iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			System.out.println(name);
		}
	}

}

package com.chengcheng.cases.type.integer;

import io.swagger.annotations.Api;

/*
 *  总结: 1. Integer 和 Integer 在 127 之前是 ==, 127 之后是 !=.
 *        2. Int 和 Integer, new Integer() 是相等的
 *        3. Integer 和 new Integer() 是不相等的
 */
@Api("演示 Int, Integer, new Integer() 之间的比较")
public class IntegerTest {

	public static void main(String[] args) {
		int i = 128;
		Integer i2 = 128;
		Integer i3 = new Integer(128);
		// Integer 会自动拆箱为int, 所以为True
		System.out.println(i == i2);  // true
		System.out.println(i == i3);  // true
		System.out.println("************************");
		Integer i5 = 127;  // java在编译的时候,被翻译成-> Integer i5 = Integer.valueOf(127);
		Integer i6 = 127;
		System.out.println(i5 == i6);  // true
		Integer i7 = 128;
		Integer i8 = 128;
		System.out.println(i7 == i8);  // fasle
		System.out.println("************************");
		Integer ii5 = new Integer(127);
		System.out.println(i5 == ii5);  // false
		Integer i9 = new Integer(128);
		Integer i10 = new Integer(123);
		System.out.println(i9 == i10);

	}
}

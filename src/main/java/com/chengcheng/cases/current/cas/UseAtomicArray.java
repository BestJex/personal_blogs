package com.chengcheng.cases.current.cas;

import io.swagger.annotations.Api;

import java.util.concurrent.atomic.AtomicIntegerArray;

@Api("CAS原子类操作 - AtomicIntegerArray")
public class UseAtomicArray {
	static int[] value = new int[] {1, 2};
	static AtomicIntegerArray ai = new AtomicIntegerArray(value);

	public static void main(String[] args) {
		ai.getAndSet(1, 3);
		System.out.println(ai.get(0));
		System.out.println(value[1]);  // 原来数组不会变化
	}
}

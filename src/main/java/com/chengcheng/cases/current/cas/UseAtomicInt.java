package com.chengcheng.cases.current.cas;

import io.swagger.annotations.Api;

import java.util.concurrent.atomic.AtomicInteger;

@Api("基本类型的原子操作类")
public class UseAtomicInt {

	static AtomicInteger ai = new AtomicInteger(10);

	public static void main(String[] args) {
		ai.getAndIncrement();  // 原子上增加一个当前值 i ++
		ai.incrementAndGet();  // 原子上增加一个当前值 ++ i
		ai.compareAndSet(25, 100);  // 如果当前值 == 为预期值, 则将该值原子设置为给定的更新值
		ai.addAndGet(75);  // 将给定的值原子地添加到当前值
		ai.get();  // 获取当前值
		System.out.println(ai.get());
	}



}

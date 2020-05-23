package com.chengcheng.cases.current.single;

import io.swagger.annotations.Api;

@Api("懒汉式 - 双重检查")
public class SingleDcl {
	private volatile static SingleDcl singleDcl;
	private SingleDcl(){}  // 私有化

	public static SingleDcl getInstance() {
		if (singleDcl == null) {  // 第一次检查, 不加锁
			System.out.println(Thread.currentThread() + "is null");
			synchronized (SingleDcl.class) {  // 加锁
				if (singleDcl == null) {  // 第二次检查, 加锁情况下
					System.out.println(Thread.currentThread() + "is null");
					// 内存里分配空间 1
					// 空间的地址给singleDcl 3
					// 空间初始化 2
					singleDcl = new SingleDcl();
				}
			}
		}
		return singleDcl;
	}
}

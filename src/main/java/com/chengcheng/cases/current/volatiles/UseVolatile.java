package com.chengcheng.cases.current.volatiles;

import io.swagger.annotations.Api;

import java.util.concurrent.TimeUnit;

/**
 * - 可见性, 对一个volatile变量的读, 总是能看到(任意线程)对这个volatile变量最后的写入.
 * - 原子性, 对于任意个volatile变量的读/写具有原子性, 但类似于volatile++这种复合操作不具有原子性.
 */
@Api("Volatile - 适合于只有一个线程写, 多个线程读的场景, 因为它只能确保可见性.")
public class UseVolatile {

	volatile boolean flag = true;  // 使用volatile的情况下
//	boolean flag = true;  // 使用volatile的情况下

	/* 被调用的方法 */
	void method() {
		System.out.println("start");
		while (flag) { }
		System.out.println("end");
	}

	/* 主函数测试方法 */
	public static void main(String[] args) throws InterruptedException {
		final UseVolatile useVolatile = new UseVolatile();
		new Thread(new Runnable() {
			@Override
			public void run() {
				useVolatile.method();
			}
		}).start();

		TimeUnit.SECONDS.sleep(1);  // 睡眠一秒, CPU可能会中断.
		useVolatile.flag = false;  // 赋值为false.
	}

}

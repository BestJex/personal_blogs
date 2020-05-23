package com.chengcheng.cases.current.cas;

import io.swagger.annotations.Api;

import java.util.concurrent.atomic.AtomicStampedReference;

@Api("CAS原子操作 - AtomicStampedReference 版本号保证原子性")
public class UseAtomicStampedReference {

	// 创建一个新的AtomicStampedReference与给定的初始值
	static AtomicStampedReference<String> asr = new AtomicStampedReference<>("Mark", 0);

	/* 主函数测试类 */
	public static void main(String[] args) throws InterruptedException {
		// 拿到当前的版本号(旧)
		final int oldStamp = asr.getStamp();
		final String oldReference = asr.getReference();
		System.out.println(oldReference + "======" + oldStamp);

		Thread rightStampThread = new Thread((Runnable) () -> {
			/* 参考的预期值, 参考的新值, 邮票的新价值, 邮票的新价值 || 成功返回: True*/
			System.out.println(Thread.currentThread().getName() + "当前变量值: " + oldReference + "当前版本戳" + oldStamp + "-"
					+ asr.compareAndSet(oldReference, oldReference + " + Java", oldStamp, oldStamp + 1));
		});

		Thread errorStampThread = new Thread((Runnable) () -> {
			/* 参考的预期值, 参考的新值, 邮票的新价值, 邮票的新价值 || 成功返回: True*/
			System.out.println(Thread.currentThread().getName() + "当前变量值: " + oldReference + "当前版本戳" + oldStamp + "-"
					+ asr.compareAndSet(oldReference, oldReference + " + Go", oldStamp, oldStamp + 1));
		});

		rightStampThread.start();
		errorStampThread.start();
		errorStampThread.join();
		rightStampThread.join();

		System.out.println(asr.getReference() + "======" + asr.getStamp());
	}

}

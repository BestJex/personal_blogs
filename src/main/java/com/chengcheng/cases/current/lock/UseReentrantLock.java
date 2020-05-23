package com.chengcheng.cases.current.lock;

import io.swagger.annotations.Api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Api("使用Lock的范例")
public class UseReentrantLock {

	private Lock lock = new ReentrantLock(true); // true: 为公平锁, 默认: 非公平锁. => 非公平锁的效率更高.
	private int age = 100000;  // 初始值

	private int getAge() {
		return age;
	}

	private static class TestThread extends Thread {

		private UseReentrantLock useReentrantLock;

		TestThread(UseReentrantLock useReentrantLock, String name) {
			super(name);
			this.useReentrantLock = useReentrantLock;
		}

		@Override
		public void run() {
			for (int i = 0; i < 100000; i++) {
				useReentrantLock.test();
				System.out.println(" ++: " + useReentrantLock.getAge());
			}
			System.out.println(Thread.currentThread().getName() + " age = " + useReentrantLock.getAge());
		}
	}

	/* 加锁++操作 */
	public void test() {
		lock.lock();
		try {
			age++;
		} finally {
			lock.unlock();  // 释放锁
		}
	}

	/* 加锁--操作 */
	private void test2() {
		lock.lock();
		try {
			age--;
		} finally {
			lock.unlock();  // 释放锁
		}
	}

	/* 主函数测试类 */
	public static void main(String[] args) {
		UseReentrantLock useReentrantLock = new UseReentrantLock();
		Thread endThread = new TestThread(useReentrantLock, "endThread");
		endThread.start();

		for (int i = 0; i < 100000; i++) {
			useReentrantLock.test2();
			System.out.println(" --: " + useReentrantLock.getAge());
		}
		System.out.println("Thread.currentThread().getName()" + " age = " + useReentrantLock.getAge());
	}

}

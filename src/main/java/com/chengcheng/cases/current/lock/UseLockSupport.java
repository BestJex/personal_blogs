package com.chengcheng.cases.current.lock;

import io.swagger.annotations.Api;

import java.util.concurrent.locks.LockSupport;

/**
 * 重入锁使基于Lock接口, 通过AQS实现接口lock和unlock方法,分为公平锁和非公平锁,
 * 默认为非公平锁. 而LockSupport使通过unsafe类直接调用本地代码, 与平台无关.
 */
@Api("LockSupport的使用")
public class UseLockSupport {

	static class MyThread extends Thread {
		private boolean isPark = false;

		@Override
		public void run() {
			System.out.println("Enter Thead running ....");
			while (true) {
				if (isPark) {
					System.out.println(Thread.currentThread().getName() + "Thread is Park ....");
					LockSupport.park();  // 线程等待
				}
				System.out.println(Thread.currentThread().getName() + " >> is running.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		void park() {
			isPark = true;
		}

		void unPark() {
			isPark = false;
			LockSupport.unpark(this);
			System.out.println("Thread is unPark ....");
		}
	}

	/* 主函数测试 */
	public static void main(String[] args) {
		MyThread myThread = new MyThread();
		myThread.setName("myThread");
		myThread.start();
		try {
			Thread.sleep(10);
			myThread.park();
			Thread.sleep(10000);
			myThread.unPark();
			Thread.sleep(10000);
			myThread.park();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

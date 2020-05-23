package com.chengcheng.cases.current.thread;

import io.swagger.annotations.Api;

@Api("Sychronized关键字的使用方法")
public class SynchronizedCase {

	private long count = 0;
	private Object obj = new Object();  // 作为一个锁

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	/* 用在同步块上 */
	public void inCount() {
		synchronized (obj) {
			count++;
			// 抛出异常....
		}
	}

	/*用在方法上*/
	private synchronized void inCount2() {
		count++;
	}

	/*用在同步块上，但是锁的是当前类的对象实例*/
	public void inCount3() {
		synchronized (this) {
			count++;
		}

	}

	// 线程
	private static class Count extends Thread {
		private SynchronizedCase synchronizedCase;

		public Count(SynchronizedCase synchronizedCase) {
			this.synchronizedCase = synchronizedCase;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				synchronizedCase.inCount3();  // count = count+10000
			}
		}
	}

	// 测试
	public static void main(String[] args) throws InterruptedException {
		SynchronizedCase synchronizedCase = new SynchronizedCase();
		// 启动两个线程
		Count count1 = new Count(synchronizedCase);
		Count count2 = new Count(synchronizedCase);
		count1.start();
		count2.start();
		Thread.sleep(50);
		System.out.println(synchronizedCase.count);  // 20000
	}
}

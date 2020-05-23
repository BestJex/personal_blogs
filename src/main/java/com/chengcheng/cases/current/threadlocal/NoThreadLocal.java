package com.chengcheng.cases.current.threadlocal;

import io.swagger.annotations.Api;

@Api("不使用ThreadLocal的情况下.")
public class NoThreadLocal {

	static Integer count = 1;

	public static class TestTask implements Runnable {

		int id;
		public TestTask(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + ":start.");
			count += id;
			System.out.println(Thread.currentThread().getName() + ":" + count);
		}
	}

	/* 运行三个线程 */
	public void StartThreadArray() {
		Thread[] runs = new Thread[3];
		for (int i = 0; i < runs.length; i ++) {
			runs[i] = new Thread(new TestTask(i));
		}
		for (Thread run : runs) {
			run.start();
		}
	}

	/* 测试 */
	public static void main(String[] args) {
		NoThreadLocal test = new NoThreadLocal();
		test.StartThreadArray();
	}

}

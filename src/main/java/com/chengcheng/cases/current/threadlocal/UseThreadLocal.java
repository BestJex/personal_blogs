package com.chengcheng.cases.current.threadlocal;

import io.swagger.annotations.Api;

@Api("使用ThreadLocal")
public class UseThreadLocal {

	private static ThreadLocal<Integer> intLocal
			= new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 1;
		}
	};

	private static ThreadLocal<Integer> intLocal2 = new ThreadLocal<Integer>();
	//intLocal2.set()
	private static ThreadLocal<String> stringThreadLocal;

	/**
	 * 运行3个线程
	 */
	public void StartThreadArray() {
		Thread[] runs = new Thread[5];
		for (int i = 0; i < runs.length; i++) {
			runs[i] = new Thread(new TestThread(i * 2));  // 自己操作自己的 ThreadLocal => intLocal变量
		}
		for (Thread run : runs) {
			run.start();
		}
	}

	/**
	 * 类说明：测试线程，线程的工作是将ThreadLocal变量的值变化，
	 * 并写回，看看线程之间是否会互相影响
	 */
	public static class TestThread implements Runnable {
		int id;

		TestThread(int id) {
			this.id = id;
		}

		public void run() {
//			System.out.println(Thread.currentThread().getName() + ":start");
			Integer s = intLocal.get();
			s += id;
			intLocal.set(s);
			System.out.println(Thread.currentThread().getName() + ":" + intLocal.get());
//			intLocal.remove();
		}
	}

	public static void main(String[] args) {
		UseThreadLocal test = new UseThreadLocal();
		test.StartThreadArray();
	}
}

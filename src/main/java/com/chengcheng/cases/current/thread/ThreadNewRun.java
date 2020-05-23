package com.chengcheng.cases.current.thread;

import io.swagger.annotations.Api;

@Api("新起线程的两种方式Thread && Runnable")
public class ThreadNewRun {

	/* 扩展自Thread类 */
	private static class UseThread extends Thread {

		@Override
		public void run() {
			System.out.println("I am extends thread ~");
		}
	}

	/* 实现Runnable接口 */
	private static class UseRunnable implements Runnable {

		@Override
		public void run() {
			System.out.println("I am implements Runnable ~");
		}
	}


	public static void main(String[] args) {
		UseThread useThread = new UseThread();
		useThread.setPriority(5);  // 1-10  os 1,2,3 => 设置线程执行的优先级
		useThread.start();

		UseRunnable useRunnable = new UseRunnable();
		new Thread(useRunnable).start();

	}

}

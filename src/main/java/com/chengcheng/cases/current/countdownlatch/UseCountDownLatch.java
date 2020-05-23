package com.chengcheng.cases.current.countdownlatch;

import io.swagger.annotations.Api;

import java.util.concurrent.CountDownLatch;

@Api("CountDownLatch的使用场景")
public class UseCountDownLatch {

	static CountDownLatch latch = new CountDownLatch(4);

	/* 初始化线程(只有一步), 有2个 */
	private static class InitThread implements Runnable {
		@Override
		public void run() {
			System.out.println("线程_" + Thread.currentThread().getId() + "准备初始化工作...");
			latch.countDown();  // 初始化线程完成工作了, countDown方法值扣减一次.
			for (int i = 0; i < 2; i ++) {
				System.out.println("线程_" + Thread.currentThread().getId() + "工作中...");
			}
		}
	}

	/* 业务线程 */
	private static class ServiceThread implements Runnable {
		@Override
		public void run() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i = 0; i < 2; i ++) {
				System.out.println("业务线程_" + Thread.currentThread().getId() + "工作.................");
			}
		}
	}

	/* 测试 */
	public static void main(String[] args) throws Exception {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("线程____" + Thread.currentThread().getId() + "@@@ 01......");
				latch.countDown();  // 每完成一步初始化工作, 扣减一次.

				System.out.println("线程____" + Thread.currentThread().getId() + "@@@ 02......");
				latch.countDown();  // 每完成一步初始化工作, 扣减一次.
			}
		}).start();

		new Thread(new ServiceThread()).start();  // 先启动业务线程, 但是由于latch.await(), 要等待...
		// 启动两个初始化任务线程, 初始化线程中有latch.countDown(), 当累计初始化线程减2次 + 主线程减两次, 业务线程则等待被唤醒,并执行...
		for(int i = 0; i < 2; i ++){
			Thread thread = new Thread(new InitThread());
			thread.start();
		}
		latch.await();
		System.out.println("主线程工作完毕...");
	}

}

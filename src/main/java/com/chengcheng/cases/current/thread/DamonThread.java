package com.chengcheng.cases.current.thread;

import io.swagger.annotations.Api;

/**
 *  线程有两种：前台线程和后台线程。
 *  区别是：应用程序必须运行完所有的前台线程才可以退出；而对于后台线程，应用程序则可以不考虑其是否已经运行完毕而直接退出，所有的后台线程在应用程序退出时都会自动结束。
 *  注意: 守护线程中finally快不一定起作用...
 */
@Api("多线程案例之 - 后台线程")
public class DamonThread implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i ++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "-------- is running...");
		}

	}

	// DamonThread.java - 后台线程测试
	public static void main(String[] args) {
		System.out.println("Main线程是后台线程吗?" + Thread.currentThread().isDaemon());  // 判断是否是后台线程
		DamonThread dt = new DamonThread();
		Thread thread = new Thread(dt, "后台线程");
		System.out.println("thread线程是后台线程吗?" + thread.currentThread().isDaemon());
//		thread.setDaemon(true);  // 将线程thread设置成后台线程  该设置必须要在该线程启动之前
		thread.start();

		// Main中的循环
		for (int i = 0; i < 10; i ++) {
			System.out.println("前台线程:" + i);
		}
	}

}

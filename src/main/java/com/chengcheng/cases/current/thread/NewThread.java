package com.chengcheng.cases.current.thread;

import io.swagger.annotations.Api;

@Api("多线程案例之 - 前台线程")
public class NewThread implements Runnable {

	public void run() {
		try {
			for(int i = 0; i < 5; i ++) {
				Thread th = Thread.currentThread();  // 获取当前线程
				String th_name = th.getName();  // 获取当前线程名字
				System.out.println("方法线程名称:" + th_name + " || 第" + i + "个");
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("=============方法线程结束============");
	}

	public static void main(String args[]) {
		NewThread newThread = new NewThread();  // 创建一个新线程
		Thread thread = new Thread(newThread, "线程1");
		thread.start();
		try {
			for (int i = 0; i < 5; i ++) {
				System.out.println("主函数线程: " + i);
				Thread.sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("==============主函数线程结束=============.");
	}
}


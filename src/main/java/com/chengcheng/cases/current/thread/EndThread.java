package com.chengcheng.cases.current.thread;

import io.swagger.annotations.Api;


@Api("安全中断线程的方式")
public class EndThread {

	private static class UseThread extends Thread {

		public UseThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " interrrupt flag =" + isInterrupted());
//			while(!isInterrupted()){  // 判断是否为true, 如果是true则中断, 最终为True.
			while (!Thread.interrupted()) {  // 判断是否为true, 如果是true则中断, 最后将True改为false, 中断后最终为False.
				System.out.println(threadName + " is running");
				System.out.println(threadName + "inner interrrupt flag =" + isInterrupted());
			}
			System.out.println(threadName + " interrrupt flag =" + isInterrupted());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread endThread = new UseThread("endThread");
		endThread.start();
		Thread.sleep(20);
		endThread.interrupt();  // 中断线程,其实设置线程的标识位true.
	}


}

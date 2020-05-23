package com.chengcheng.cases.current.thread;

import io.swagger.annotations.Api;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

@Api("演示Join() 方法的使用 - 线程插队.")
public class UseJoin {

	/*lison心中的女神*/
	static class Goddess implements Runnable {

		private Thread thread;
		public Goddess() { }
		Goddess(Thread thread) {
			this.thread = thread;
		}

		@Override
		public void run() {
			System.out.println("Goddess开始排队打饭...");
			try {
				if (thread != null) {
					thread.join();
				}
				Thread.sleep(2000);
				System.out.println(thread.currentThread().getName() + " Goddess打饭完成...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*lison心中的女神的男朋友*/
	static class GoddessBoyFriend implements Runnable {
		@Override
		public void run() {
			try {
				System.out.println("GoddessBoyfriend开始排队打饭.....");
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " GoddessBoyfriend打饭完成...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		/*三个人都去打饭*/
		Thread lison = Thread.currentThread();

		GoddessBoyFriend goddessBoyfriend = new GoddessBoyFriend();
		Thread gbf = new Thread(goddessBoyfriend);
		gbf.start();

		Goddess goddess = new Goddess(gbf); // goddessBoyfriend来了.
		Thread g = new Thread(goddess);
		g.start();

		System.out.println("lison开始排队打饭.....");
		g.join();  // 等待女神打完饭.
		Thread.sleep(2000);  // 让主线程休眠2秒.
		System.out.println(Thread.currentThread().getName() + " lison打饭完成.");
	}




}

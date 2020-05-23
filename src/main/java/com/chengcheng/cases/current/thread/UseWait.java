package com.chengcheng.cases.current.thread;

import io.swagger.annotations.Api;

/**
 * * wait方法由三个构造方法
 * * - 1. wait()
 * * - 2. wait(Long)
 * * - 3. wait(Long, Int)
 * <p>
 * 首先, 这要非常注意的几个事实:
 * 1. 任何一个时刻, 对象的控制权只能被一个线程拥有.
 * 2. 无论使执行对象的wait, notify, notifyAll方法, 必须保证当前运行的线程取得了该对象的控制权.
 * 3. 入宫再没有控制权的线程里执行对象的以上三种方法, 就会报java.lang.IllegalMonitorStateException异常.
 * 4. JVM基于多线程,默认情况下不能保证运行时线程的时序性.
 * <p>
 * 基于以上几点事实，我们需要确保让线程拥有对象的控制权。
 * - 1. 也就是说在waitThread中执行wait方法时，要保证waitThread对flag有控制权.
 * - 2. 在notifyThread中执行notify方法时，要保证notifyThread对flag有控制权.
 */
@Api("wait、notify、notifyAll的使用 - 线程等待, 同为对象上的方法.")
public class UseWait {

	/**
	 * - 对在同步块中对flag进行了赋值操作，使得flag引用的对象改变，这时候再调用notify方法时，因为没有控制权所以抛出异常。
	 * - 我们可以改进一下，将flag改成一个JavaBean，然后更改它的属性不会影响到flag的引用。
	 */
	private String flag[] = {"true"};

	/* 通知类 notify(), notifyAll() */
	class NotifyThread extends Thread {
		NotifyThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			try {
				sleep(2000);  // 模拟业务推迟2秒钟通知
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (flag) {
				flag[0] = "false";
//				flag.notify();  // 通知某个正在等待这个对象的控制全的线程可以继续运行.
				flag.notifyAll();  // 通知所有等待这个对象控制权的线程继续运行.
			}
		}
	}

	/* 等待类 wait() */
	class WaitThread extends Thread {
		WaitThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			synchronized (flag) {
				while (!"false".equals(flag[0])) {
					System.out.println(getName() + " 开始等待!");
					long waitTime = System.currentTimeMillis();
					try {
						flag.wait();  // 使持有该对象的线程把该对象的控制权交出去, 然后处于等待状态.
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					waitTime = System.currentTimeMillis() - waitTime;
					System.out.println("等待时间为: " + waitTime);
				}
				System.out.println(getName() + " 结束等待!");
			}
		}
	}

	/* 主线程测试 */
	public static void main(String[] args) throws InterruptedException {
		UseWait test = new UseWait();
		NotifyThread notifyThread = test.new NotifyThread("notify01");
		WaitThread waitThread01 = test.new WaitThread("wait01");
		WaitThread waitThread02 = test.new WaitThread("wait02");
		WaitThread waitThread03 = test.new WaitThread("wait03");
		notifyThread.start();
		waitThread01.start();
		waitThread02.start();
		waitThread03.start();
	}


}

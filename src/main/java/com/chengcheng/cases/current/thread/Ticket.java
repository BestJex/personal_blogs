package com.chengcheng.cases.current.thread;

import io.swagger.annotations.Api;

@Api("线程同步方法")
public class Ticket implements Runnable {

	private int tickets = 100;

	@Override
	public void run() {
		while (true) {
			saleTicket();  // 调用售票方法
			if (tickets <= 0) {
				break;
			}
		}
	}

	// 定义一个同步的方法
	private synchronized void saleTicket() {
		if (tickets > 0) {
			try {
				Thread.sleep(1000);  // 线程睡眠一秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "---卖出的票" + tickets --);
		}
	}

	// Ticket.java
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		new Thread(ticket, "线程一").start();
		new Thread(ticket, "线程二").start();
		new Thread(ticket, "线程三").start();
		new Thread(ticket, "线程四").start();
	}
}

package com.chengcheng.cases.source.pool;

import io.swagger.annotations.Api;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Api("手写连接池测试")
public class DBPoolTest {

	static DBPool pool = new DBPool(10);
	static CountDownLatch end;  // 控制器: 控制Main线程将会等待所有Worker结束后才能继续执行.

	/* 任务方法 */
	static class Worker implements Runnable {

		int count;
		AtomicInteger got;
		AtomicInteger notGot;

		/* 构造方法 */
		Worker(int count, AtomicInteger got, AtomicInteger notGot) {
			this.count = count;  // 每个线程操作的次数.
			this.got = got;  // 计数器: 统计可以拿到连接的线程
			this.notGot = notGot;  // 技术器: 统计没有拿到连接的线程
		}

		@Override
		public void run() {
			while (count > 0) {
				try {
					// 从线程池中获取连接, 如果一秒内无法获取到, 将会返回null, 分别统计连接获取的数量got和为获取到的数量notGot
					Connection connection = pool.fetchConnection(1000);
					if (connection != null) {
						try {
							connection.createStatement();
/*                            PreparedStatement preparedStatement = connection.prepareStatement("");
                            preparedStatement.execute();*/
							connection.commit();
						} finally {
							pool.releaseConnection(connection);  // 释放连接
							System.out.println(Thread.currentThread().getName() + "__已成功拿到连接...");
							got.incrementAndGet();  // 连接数量 + 1
						}
					} else {
						notGot.incrementAndGet();  // 连接失败数量 + 1
						System.out.println(Thread.currentThread().getName() + "__连接等待超时...");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					count--;
				}
			}
			end.countDown();
		}
	}

	/* 主函数测试类 */
	public static void main(String[] args) throws InterruptedException {
		int threadCount = 50;  // 线程数量
		end = new CountDownLatch(threadCount);
		int count = 20;  // 每个线程操作次数
		AtomicInteger got = new AtomicInteger();  // 计数器：统计可以拿到连接的线程
		AtomicInteger notGot = new AtomicInteger();  // 计数器：统计没有拿到连接的线程
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new Worker(count, got, notGot), "Worker" + i);
			thread.start();
		}
		end.await();  // Main线程在此处等待
		System.out.println("总共尝试了: " + (threadCount * count));
		System.out.println("拿到连接的次数: " + got);
		System.out.println("没能连接的次数: " + notGot);
	}

}

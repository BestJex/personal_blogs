package com.chengcheng.cases.current.lock;

import io.swagger.annotations.Api;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * - Lock 替代了 synchronized 方法和语句的使用.
 * - Condition 替代了 Object(wait, notify, notifyAll) 监视器方法的使用.
 */
@Api("演示Lock和Condition的结合使用")
public class UseCondition {

	final Lock lock = new ReentrantLock();  // 锁对象
	final Condition notFull = lock.newCondition();  // 写线程条件
	final Condition notEmpty = lock.newCondition(); // 读线程条件
	final Integer[] items = new Integer[10];  // 缓存队列
	int putptr, takeptr, count; // 写索引, 读索引, 队列中存在的数据个数.

	/* 生产端 */
	public void put(Integer x) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				notFull.await();  // 如果队列满了, 阻塞"写线程".
			}
			items[putptr] = x;  // 赋值
			System.out.println("写入: " + x);
			if (++putptr == items.length) {
				putptr = 0;  // 如果写索引写到队列的最后一个位置了, 那么置为0.
			}
			++count; // 个数 ++
			notEmpty.signal();  // 唤醒读线程
		} finally {
			lock.unlock();
		}
	}

	/* 消费端 */
	public Integer take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				notEmpty.await();  // 如果队列为空, 阻塞"读线程"
			}
			Integer x = items[takeptr];  // 取值
			System.out.println("读取: " + x);
			if (++takeptr == items.length) {
				takeptr = 0;  // 如果读取索引读到队列的最后一个位置了, 那么置为0
			}
			--count;  // 个数 --
			notFull.signal();  // 唤醒写线程
			return x;
		} finally {
			lock.unlock();
		}
	}

	/* 主函数测试 */
	public static void main(String[] args) {
		final UseCondition b = new UseCondition();

		new Thread((Runnable) () -> {
			int i = 0;
			while (true) {
				try {
					b.put(i++);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		new Thread((Runnable) () -> {
			while (true) {
				try {
					b.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}

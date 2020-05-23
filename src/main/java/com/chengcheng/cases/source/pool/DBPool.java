package com.chengcheng.cases.source.pool;

import io.swagger.annotations.Api;

import java.sql.Connection;
import java.util.LinkedList;

@Api("手写连接池逻辑")
public class DBPool {

	private static LinkedList<Connection> pool = new LinkedList<>();  // 连接池 - 连接的容器

	/**
	 * 连接池构造方法
	 *
	 * @param initialSize 初始化大小
	 */
	public DBPool(int initialSize) {
		if (initialSize > 0) {
			for (int i = 0; i < initialSize; i++) {
				pool.addLast(SqlConnectImpl.fetchConnection());
			}
		}
	}

	/**
	 * 释放连接, 通知等待数据库的线程
	 */
	public void releaseConnection(Connection connection) {
		if (connection != null) {
			synchronized (pool) {
				// 添加后需要进行通知,这样其它消费者能够感知到连接池中已经归还了一个连接.
				pool.addLast(connection);
				pool.notifyAll();  // 通知
			}
		}
	}

	/**
	 * 获取连接, 通知等待数据库连接的线程
	 * 在mills内无法获取到连接, 将会返回null
	 */
	public Connection fetchConnection(long mills) throws InterruptedException {
		synchronized (pool) {
			if (mills <= 0) {  // 永不超时
				while (pool.isEmpty()) {
					pool.wait();
				}
				return pool.removeFirst();  // 一直等待, 直到拿到返回连接
			} else {
				long future = System.currentTimeMillis() + mills;  // 超时时间
				long remaining = mills;  // 等待时长
				// 如果池为空且未超时, 则继续等待
				while (pool.isEmpty() && remaining > 0) {
					pool.wait(remaining);
					remaining = future - System.currentTimeMillis();  // 每被唤醒一次, 重新计算等待时长
				}
				Connection result = null;
				// 如果池子不为空
				if (!pool.isEmpty()) {
					result = pool.removeFirst();
				}
				return result;  // 直接拿到返回
			}
		}
	}
}

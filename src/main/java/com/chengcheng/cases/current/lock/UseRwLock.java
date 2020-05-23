package com.chengcheng.cases.current.lock;

import io.swagger.annotations.Api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Api("读写锁操作 - 1")
public class UseRwLock {

	/* 商品实体类 */
	public static class GoodsInfo {
		private final String name;
		private double totalMoney;  // 总销售额
		private int storeNumber;  // 库存数

		public GoodsInfo(String name, int totalMoney, int storeNumber) {
			this.name = name;
			this.totalMoney = totalMoney;
			this.storeNumber = storeNumber;
		}

		public double getTotalMoney() {
			return totalMoney;
		}

		public int getStoreNumber() {
			return storeNumber;
		}

		public void changeNumber(int sellNumber){
			this.totalMoney += sellNumber * 25;
			this.storeNumber -= sellNumber;
		}
	}

	public static class UseRWLock {
		private GoodsInfo goodsInfo;
		private final ReadWriteLock lock = new ReentrantReadWriteLock();  // 读写锁
		private final Lock getLock = lock.readLock();  // 读锁
		private final Lock setLock = lock.writeLock();  // 写锁

		public UseRWLock(GoodsInfo goodsInfo) {
			this.goodsInfo = goodsInfo;
		}

		/* 读锁 */
		public GoodsInfo getNum() throws InterruptedException {
			getLock.lock();
			try {
				Thread.sleep(5);
				return this.goodsInfo;
			} finally {
				getLock.unlock();
			}
		}

		/* 写锁 */
		public void setNum(int number) throws InterruptedException {
			setLock.lock();
			try {
				Thread.sleep(5);
				goodsInfo.changeNumber(number);
			} finally {
				setLock.unlock();
			}
		}

	}

}

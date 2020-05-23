package com.chengcheng.cases.current.lock.trylockcase;

import io.swagger.annotations.Api;

import java.util.Random;

@Api("不会产生死锁的安全转账，尝试拿锁.")
public class SafeOperateToo implements ITransfer {

	@Override
	public void transfer(UserAccount from, UserAccount to, int amount)
			throws InterruptedException {
		Random r = new Random();
		while (true) {
			if (from.getLock().tryLock()) {
				try {
					System.out.println(Thread.currentThread().getName() + " get " + from.getName());
					if (to.getLock().tryLock()) {
						try {
							System.out.println(Thread.currentThread().getName() + " get " + to.getName());
							//两把锁都拿到了
							from.flyMoney(amount);
							to.addMoney(amount);
							break;
						} finally {
							to.getLock().unlock();
						}
					}
				} finally {
					from.getLock().unlock();
				}
			}
			Thread.sleep(r.nextInt(10));
		}
	}
}
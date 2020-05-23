package com.chengcheng.cases.current.lock.trylockcase;

import io.swagger.annotations.Api;

@Api("不安全的转账动作的实现. - 不安全")
public class TransferAccount implements ITransfer {
	@Override
	public void transfer(UserAccount from, UserAccount to, int amount)
			throws InterruptedException {
		synchronized (from) {
			System.out.println(Thread.currentThread().getName()
					+ " get" + from.getName());
			Thread.sleep(100);
			synchronized (to) {
				System.out.println(Thread.currentThread().getName()
						+ " get" + to.getName());
				from.flyMoney(amount);
				to.addMoney(amount);
			}
		}
	}
}

package com.chengcheng.cases.current.lock.trylockcase;

import io.swagger.annotations.Api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Api("用户账户的实体类")
public class UserAccount {
	private final String name;  // 账户名称
	private int money;  // 账户余额
	private final Lock lock = new ReentrantLock();  //显示锁

	public UserAccount(String name, int amount) {
		this.name = name;
		this.money = amount;
	}

	public String getName() {
		return name;
	}

	public int getAmount() {
		return money;
	}

	public Lock getLock() {
		return lock;
	}

	@Override
	public String toString() {
		return "UserAccount{" +
				"name='" + name + '\'' +
				", money=" + money +
				'}';
	}

	/* 转入资金 */
	public void addMoney(int amount) {
		money = money + amount;
	}

	/* 转出资金 */
	public void flyMoney(int amount) {
		money = money - amount;
	}
}

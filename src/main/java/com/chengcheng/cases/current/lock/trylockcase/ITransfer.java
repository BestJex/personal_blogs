package com.chengcheng.cases.current.lock.trylockcase;

import io.swagger.annotations.Api;

@Api("银行转账动作接口")
public interface ITransfer {
	/**
	 * @param from   转出账户
	 * @param to     转入账户
	 * @param amount 转账金额
	 */
	void transfer(UserAccount from, UserAccount to, int amount)
			throws InterruptedException;
}

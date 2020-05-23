package com.chengcheng.cases.deep.design.ceLue;

/**
 * 3 创建Context类, 提供给调用实例化
 */
public class Context {
	private Strategy strategy;

	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	public int executeStrategy(int num1, int num2) {
		return strategy.doOperation(num1, num2);
	}
}

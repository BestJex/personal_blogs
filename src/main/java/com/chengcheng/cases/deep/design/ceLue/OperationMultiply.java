package com.chengcheng.cases.deep.design.ceLue;

/**
 * 2.3 创建实现接口的实体类
 */
public class OperationMultiply implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 * num2;
	}
}

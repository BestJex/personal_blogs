package com.chengcheng.cases.deep.design.ceLue;

/**
 * 2.2 创建实现接口的实体类
 */
public class OperationSubstract implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 - num2;
	}

}

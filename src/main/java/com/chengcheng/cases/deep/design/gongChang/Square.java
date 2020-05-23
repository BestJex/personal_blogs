package com.chengcheng.cases.deep.design.gongChang;

/**
 *  2.2 创建实现就扣的实体类
 */
public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Inside Square::draw() method.");
	}
}
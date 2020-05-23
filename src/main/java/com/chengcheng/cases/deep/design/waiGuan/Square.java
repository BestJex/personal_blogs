package com.chengcheng.cases.deep.design.waiGuan;

/**
 *  2.2 创建实现接口的实体类
 */
public class Square implements Shape {

	@Override
	public void draw() {
		System.out.println("Square::draw()");
	}
}

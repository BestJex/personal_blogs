package com.chengcheng.cases.deep.design.waiGuan;

/**
 *  2.1 创建实现接口的实体类
 */
public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Rectangle::draw()");
	}
}

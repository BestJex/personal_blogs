package com.chengcheng.cases.deep.design.daiLi;

/**
 *  3. 当被请求时, 使用proxyImage来获取RealImage类的对象.
 */
public class ProxyPatternDemo {

	public static void main(String[] args) {

		Image image = new ProxyImage("test_demo.jpg");
		// 图像第一次将从磁盘加载
		image.display();
		System.out.println("--------");
		// 图像不需要从磁盘加载
		image.display();
	}
}

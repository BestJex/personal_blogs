package com.chengcheng.cases.deep.design.daiLi;

/**
 *  2.2 创建实现接口的实体类
 */
public class ProxyImage implements Image {

	private RealImage realImage;
	private String fileName;

	public ProxyImage(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void display() {
		// 判断是否为第一次
		if (realImage == null) {
			// 创建新对象嗲用构造方法,构造方法中调用loadFromDisk方法
			realImage = new RealImage(fileName);
		}
		realImage.display();
	}
}

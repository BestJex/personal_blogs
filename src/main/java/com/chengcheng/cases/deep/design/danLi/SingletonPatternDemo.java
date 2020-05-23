package com.chengcheng.cases.deep.design.danLi;

/**
 *  2. 从singleton类获取唯一的对象
 */
public class SingletonPatternDemo {

	public static void main(String[] args) {

		// 不合法的构造方法
		// 编译时错误: 构造函数 SingleObject() 是不可见的
		// SingleObject object = new SingleObject();

		// 获取唯一可用的对象
		SingleObject object = SingleObject.getInstance();

		// 显示消息
		object.showMessage();

	}

}

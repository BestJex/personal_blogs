package com.chengcheng.cases.deep.design.zhuangShiQi;

/**
 *  3. 创建实现了Shape接口抽象装饰类
 */
public abstract class ShapeDecorator implements Shape {
	protected Shape decoratedShape;

	public ShapeDecorator(Shape decoratedShape){
		this.decoratedShape = decoratedShape;
	}

	public void draw(){
		decoratedShape.draw();
	}
}

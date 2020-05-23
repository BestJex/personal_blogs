package com.chengcheng.cases.deep.design.waiGuan;

/**
 *  4. 使用该外观类画各种类型的形状
 */
public class FacadePatternDemo {

	public static void main(String[] args) {
		ShapeMaker shapeMaker = new ShapeMaker();

		shapeMaker.drawCircle();
		shapeMaker.drawRectangle();
		shapeMaker.drawSquare();
	}
}

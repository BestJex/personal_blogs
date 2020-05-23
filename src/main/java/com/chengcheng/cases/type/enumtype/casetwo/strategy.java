package com.chengcheng.cases.type.enumtype.casetwo;

/**
 * 枚举之 - 策略模式
 */
public class strategy {

	public enum Calculator {

		ADDITION {
			public Double execute(Double x, Double y) {
				return x + y; // 加法
			}
		},

		SUBTRACTION {
			public Double execute(Double x, Double y) {
				return x - y; // 减法
			}
		},

		MULTIPLICATION {
			public Double execute(Double x, Double y) {
				return x * y; // 乘法
			}
		},


		DIVISION {
			public Double execute(Double x, Double y) {
				return x / y;  // 除法
			}
		};

		public abstract Double execute(Double x, Double y);
	}

	public static void main(String[] args) {
		System.out.println(Calculator.ADDITION.execute(4.0, 2.0));
		// 打印 6.0
		System.out.println(Calculator.SUBTRACTION.execute(4.0, 2.0));
		// 打印 2.0
		System.out.println(Calculator.MULTIPLICATION.execute(4.0, 2.0));
		// 打印 8.0
		System.out.println(Calculator.DIVISION.execute(4.0, 2.0));
		// 打印 2.0
	}
}

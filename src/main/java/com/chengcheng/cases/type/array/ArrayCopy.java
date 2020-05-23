package com.chengcheng.cases.type.array;

import io.swagger.annotations.Api;

@Api("复制数组")
public class ArrayCopy {

	/*
	 * 反例
	 */
	void method() {
		int[] array01 = new int[100];
		for (int i = 0; i < array01.length; i ++) {
			array01[i] = i;
		}

		int[] array02 = new int[100];
		for (int i = 0; i < array02.length; i ++) {
			array02[i] = array01[i];
		}
	}

	/*
	 * 更正
	 */
	void method02() {
		int[] array01 = new int[100];
		for (int i = 0; i < array01.length; i ++) {
			array01[i] = i;
		}

		int[] array02 = new int[100];
		System.arraycopy(array01, 0, array02, 0, 100);
	}


	// 测试
	public static void main(String[] args) {
		ArrayCopy arrayCopy = new ArrayCopy();
		arrayCopy.method();  // 反例
		arrayCopy.method02();  // 正例
	}
}

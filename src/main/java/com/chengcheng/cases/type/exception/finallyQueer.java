package com.chengcheng.cases.type.exception;

/**
 *  演示: Finally中再次报异常```
 */
public class finallyQueer {

	public static void main(String[] args) {
		try {
			int i = 1 / 0;
		} catch (Exception e) {
			System.out.println("捕获异常```");
		} finally {
			int j = 1 / 0;
			System.out.println("最终执行 ...");
		}
	}
}

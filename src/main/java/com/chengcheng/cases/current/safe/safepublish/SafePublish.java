package com.chengcheng.cases.current.safe.safepublish;

import io.swagger.annotations.Api;

@Api("演示基本类型的发布 - 并发安全.")
public class SafePublish {

	private int i;

	private SafePublish() {
		i = 2;
	}

	public int getI() {
		return i;
	}

	public static void main(String[] args) {
		SafePublish safePublish = new SafePublish();
		int j = safePublish.getI();
//		i++ ;
		System.out.println("Before j=" + j);
		j = 3;
		System.out.println("After j=" + j);
		System.out.println("getI = " + safePublish.getI());
	}

}

package com.chengcheng.cases.current.safe.safepublish;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;

@Api("不安全的发布.")
public class UnSafePublish {

	private List<Integer> list = new ArrayList<>(3);  // 不安全

	public UnSafePublish() {
		list.add(1);
		list.add(2);
		list.add(3);
	}

	public List getList() {
		return list;
	}

	public static void main(String[] args) {
		UnSafePublish unSafePublish = new UnSafePublish();
		List<Integer> list = unSafePublish.getList();
		System.out.println("Before: " + list);
		list.add(4);
		System.out.println("After: " + list);
		System.out.println("getList: " + unSafePublish.getList());
	}
}

package com.chengcheng.cases.current.safe.safepublish;

import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Api("安全的发布 - 并发安全")
public class SafePublishToo {

	private List<Integer> list = Collections.synchronizedList(new ArrayList<>(3));  // Collections.synchronizedList() 并发安全

	public SafePublishToo() {
		list.add(1);
		list.add(2);
		list.add(3);
	}

	public List getList() {
		return list;
	}

	public static void main(String[] args) {
		SafePublishToo safePublishToo = new SafePublishToo();
		List<Integer> list = safePublishToo.getList();
		System.out.println("Before: " + list);
		list.add(4);
		System.out.println("After: " + list);
		System.out.println("getList: " + safePublishToo.getList());
	}

}

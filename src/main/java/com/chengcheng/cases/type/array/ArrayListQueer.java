package com.chengcheng.cases.type.array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

/**
 *  演示在增强for循环中删除list元素
 *  解决: 将增强for循环换成迭代,便可解决问题.
 *  参考: https://mp.weixin.qq.com/s?__biz=MzAxMTg2MjA2OA==&mid=2649847218&idx=2&sn=bc818a276efdb998d377bae1ea2eda1b&chksm=83bf7ce9b4c8f5ff53d68afed96db2495c3cc019c4ae3d8040038b00a6ee8e286aa48071eafe&scene=0&xtrack=1&key=603425b90464b1b4280d8410e7fa4c738d6917f78925a45194844fb9eecbb53846c846303e5ddf0c69d3cb8e0552dcfbd81dbc21b398f5d0939ed1a77ba529e79a9a07226c057c9cad0d43cb9bb7fe0f&ascene=1&uin=MjI4NDc3MDg4Mg%3D%3D&devicetype=Windows+10&version=62070158&lang=zh_CN&exportkey=ARTYoVVbQb1x3U4dNh7mESM%3D&pass_ticket=hM2xZi8PS4UfoIeTBCof8XLt757ysAbPsrl4DHCTC5rT%2F2XeTXkJkBhNtwius8IM
 */
public class ArrayListQueer {

	public static void main(String[] args) {

		List<String> arrayList01 = new ArrayList<>();
		arrayList01.add("1");
		arrayList01.add("2");
		for (String s : arrayList01) {
			if ("1".equals(s)) {
				arrayList01.remove(s);
			}
		}
		System.out.println(arrayList01);

//		List<String> arrayList02 = new ArrayList<>();
//		arrayList02.add("2");
//		arrayList02.add("1");
//		for (String s : arrayList02) {
//			if ("1".equals(s)) {
//				arrayList02.remove(s);
//			}
//		}
//		System.out.println(arrayList02);

		/*
		1. 直接使用普通for循环进行操作
		我们说不能在foreach中进行，但是使用普通的for循环还是可以的，因为普通for循环并没有用到Iterator的遍历，所以压根就没有进行fail-fast的检验。
		 */
		List<String> userNames01 = new ArrayList<String>() {{
			add("Hollis");
			add("hollis");
			add("HollisChuang");
			add("H");
		}};

		for (int i = 0; i < 1; i++) {
			if (userNames01.get(i).equals("Hollis")) {
				userNames01.remove(i);
			}
		}
		System.out.println(userNames01);

		/*
		2. 直接使用Iterator进行操作
		除了直接使用普通for循环以外，我们还可以直接使用Iterator提供的remove方法。
		如果直接使用Iterator提供的remove方法，那么就可以修改到expectedModCount的值。
		 */
		List<String> userNames02 = new ArrayList<String>() {{
			add("Hollis");
			add("hollis");
			add("HollisChuang");
			add("H");
		}};

		Iterator iterator = userNames02.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().equals("Hollis")) {
				iterator.remove();
			}
		}
		System.out.println(userNames02);

		/*
		3. 使用java 8提供的filter过滤
		Java 8中可以把集合转换成流，对于流有一种filter操作， 可以对原始 Stream 进行某项测试，通过测试的元素被留下来生成一个新 Stream。
		 */
		List<String> userNames03 = new ArrayList<String>() {{
			add("Hollis");
			add("hollis");
			add("HollisChuang");
			add("H");
		}};

		userNames03 = userNames03.stream().filter(userName -> !userName.equals("Hollis")).collect(Collectors.toList());
		System.out.println(userNames03);

		/*
		4. 直接使用fail-safe的集合类
		在Java中，除了一些普通的集合类以外，还有一些采用了fail-safe机制的集合类。这样的集合容器在遍历时不是直接在集合内容上访问的，而是先复制原有集合内容，在拷贝的集合上进行遍历。
		由于迭代时是对原集合的拷贝进行遍历，所以在遍历过程中对原集合所作的修改并不能被迭代器检测到，所以不会触发ConcurrentModificationException。
		 */
		ConcurrentLinkedDeque<String> userNames04 = new ConcurrentLinkedDeque<String>() {{
			add("Hollis");
			add("hollis");
			add("HollisChuang");
			add("H");
		}};

		for (String userName : userNames04) {
			if (userName.equals("Hollis")) {
				userNames04.remove();
			}
		}

		/*
		5. 使用增强for循环其实也可以
		如果，我们非常确定在一个集合中，某个即将删除的元素只包含一个的话， 比如对Set进行操作，那么其实也是可以使用增强for循环的，只要在删除之后，立刻结束循环体，不要再继续进行遍历就可以了，也就是说不让代码执行到下一次的next方法。
		 */
		List<String> userNames05 = new ArrayList<String>() {{
			add("Hollis");
			add("hollis");
			add("HollisChuang");
			add("H");
		}};

		for (String userName : userNames05) {
			if (userName.equals("Hollis")) {
				userNames05.remove(userName);
				break;
			}
		}
		System.out.println(userNames05);

	}
}

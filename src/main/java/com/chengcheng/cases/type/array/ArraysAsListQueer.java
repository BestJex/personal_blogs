package com.chengcheng.cases.type.array;

import java.util.Arrays;
import java.util.List;

/**
 *  演示: 把数组转化成集合时, 尝试用Arrays.asList(array)
 *  坑: 1. 数组被修改后, 会直接影响到新List的值.
 *      2. 不能对新List进行add, remove等操作, 否则运行时会抛出异常
 */
public class ArraysAsListQueer {

	public static void main(String[] args) {
		Integer[] array = new Integer[]{1,2,3,4,5,6};
		List<Integer> list = Arrays.asList(array);

		// 坑1: 修改数组的值, 会直接影响原list
		System.out.println("数组被修改之前, 集合第一个元素为: " + list.get(0));
		array[0] = 10;
		System.out.println("数组被修改之前, 集合第一个元素为: " + list.get(0));

		// 坑2: 使用add remove 等操作list的方式时,会报错
		list.add(7);
	}
	/*
	*  看源码可知:
	*  Arrays.asList方法返回的List并不是java.util.arrayList,而是自己内部
	*  的一个静态类, 该静态类直接持有数组的引用,并且没有实现add, remove等方法.
	*/

	/*
	 * 同理: 对于toArray()
	 * 在使用有参toArray方法时, 申明的数组大小一定要大于等于List的大小,
	 * 如果小于的话, 你会得到一个空数组.
	 */
}

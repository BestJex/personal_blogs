package com.chengcheng.cases.type.set;


import java.util.*;

/**
 * 演示: HashSet LinkedHashSet TreeSet
 * List是有序且重复的，保证元素的添加顺序，不能保证元素的自然顺序
 * Set是无序不重复的:Set无序是指HashSet,它不能保证元素的添加顺序，更不能保证自然顺序
 * 保证元素添加的顺序：LinkedHashSet
 * 保证元素自然的顺序：TreeSet
 */
public class SetCase {

	public static void main(String[] args) {
		// HashSet  无序 不能重复
		Set<String> hashSet=new HashSet<>();
		hashSet.add("s1");
		hashSet.add("s1");
		hashSet.add("s5");
		hashSet.add("s3");
		hashSet.add("s4");
		hashSet.add("s2");
		hashSet.forEach(e ->System.out.print(e+" "));
		System.out.println();

		// LinkedHashSet 保证元素添加的顺序
		Set<String> linkedHashSet=new LinkedHashSet<>();
		linkedHashSet.add("s1");
		linkedHashSet.add("s5");
		linkedHashSet.add("s3");
		linkedHashSet.add("s4");
		linkedHashSet.add("s2");
		linkedHashSet.forEach(e ->System.out.print(e+" "));
		System.out.println();

		// 保证元素自然的顺序：TreeSet
		Set<String> treeSet=new TreeSet<>();
		treeSet.add("s1");
		treeSet.add("s5");
		treeSet.add("s3");
		treeSet.add("s4");
		treeSet.add("s2");
		treeSet.forEach(e ->System.out.print(e+" "));
		System.out.println();

	}
}

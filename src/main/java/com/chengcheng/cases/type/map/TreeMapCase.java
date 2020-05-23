package com.chengcheng.cases.type.map;

import java.util.*;

/**
 *  演示TreeMap按key 或 value 排序
 */
public class TreeMapCase {

/*	public static void main(String[] args) {
		TreeMap<String, Integer> treeMap = new TreeMap<>();
		treeMap.put("s", 2);
		treeMap.put("w", 3);
		treeMap.put("d", 1);
		treeMap.put("f", 0);
		treeMap.put("h", 9);
		treeMap.put("q", 11);
		treeMap.put("a", 25);
		//遍历1
		System.out.println("======================遍历一========================");
		Iterator<String> it1 = treeMap.keySet().iterator();
		while(it1.hasNext()){
			String key = it1.next();
			Integer value = treeMap.get(key);
			System.out.println("key=" + key + " ; " + "value=" + value);
		}

		//遍历二
		System.out.println("======================遍历二========================");
		Iterator<Map.Entry<String, Integer>> it2 = treeMap.entrySet().iterator();
		while(it2.hasNext()) {
			Map.Entry<String, Integer> entry = it2.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("key=" + key + " ; " + "value=" + value);
		}
	}*/

	public static void main(String[] args) {
		TreeMap<String, Integer> treeMap = new TreeMap<>();
		treeMap.put("s", 2);
		treeMap.put("w", 3);
		treeMap.put("d", 1);
		treeMap.put("f", 0);
		treeMap.put("h", 9);
		treeMap.put("q", 11);
		treeMap.put("a", 11);

		//treeMap在put()的时候底层会compareTo根据key升序排列
		System.out.println("treeMap = " + treeMap);

		// 根据value排序一
		List<Map.Entry<String, Integer>> entryArrayList1 = new ArrayList<>(treeMap.entrySet());
		Collections.sort(entryArrayList1, Comparator.comparing(Map.Entry::getValue));
		System.out.println("entryArrayList1=" + entryArrayList1);

		// 根据value排序二
		List<Map.Entry<String, Integer>> entryArrayList2 = new ArrayList<>(treeMap.entrySet());
		Collections.sort(entryArrayList2, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
		System.out.println("entryArrayList2=" + entryArrayList2);
		// 根据value排序三
		List<Map.Entry<String, Integer>> entryArrayList3 = new ArrayList<>(treeMap.entrySet());
		Collections.sort(entryArrayList3, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		System.out.println("entryArrayList3=" + entryArrayList3);
		//treeMap中的排序依然没变,变得只是装treeMap的Arraylist
		System.out.println("treeMap = " + treeMap);
	}

}

package com.chengcheng.cases.current.exchange;

import io.swagger.annotations.Api;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * - Exchanger是在两个任务之间交换对象的栅栏.当两个任务进入栅栏时, 它们各自拥有一个对象,当它们离开时,它们都拥有对方的对象.
 * - exchange: 交换点
 */
@Api("Exchange数据交换")
public class UseExchange {

	private static final Exchanger<Set<String>> EXCHANGER = new Exchanger<>();

	/* 主函数测试 */
	public static void main(String[] args) {

		/* 第一个线程 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				Set<String> setA = new HashSet<>();  // 存放数据的容器
				try {
					setA.add("111"); /* 添加数据 */
					Thread.sleep(2000);  // 模拟业务延时
					setA = EXCHANGER.exchange(setA);  // 交换Set
					System.out.println("setA: " + setA);  /* 处理交换后的数据 */
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		/* 第二个线程 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				Set<String> setB = new HashSet<>();  // 存放数据的容器
				try {
					setB.add("222"); /* 添加数据 */
					setB = EXCHANGER.exchange(setB);  // 交换Set
					System.out.println("setB: " + setB);  /* 处理交换后的数据 */
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}

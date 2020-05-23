package com.chengcheng.cases.current.callable;

import io.swagger.annotations.Api;

import java.util.concurrent.Callable;

@Api("继承了Callable的类")
class Task {

	static class Task01 implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			int sum = 2;
			Thread.sleep(2000); // 模拟延时
			//do something;
			return sum;
		}
	}

	static class Task02 implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			int sum = 1;
			Thread.sleep(100); // 模拟延时
			//do something;
			return sum;
		}
	}

	static class Task03 implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			int sum = 1;
			Thread.sleep(100); // 模拟延时
			//do something;
			return sum;
		}
	}

	static class Task04 implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			int sum = 2;
			Thread.sleep(2000); // 模拟延时
			//do something;
			return sum;
		}
	}




}

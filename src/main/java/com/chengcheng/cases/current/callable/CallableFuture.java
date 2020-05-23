package com.chengcheng.cases.current.callable;

import io.swagger.annotations.Api;

import java.util.concurrent.*;

@Api("Callable + Future实现线程")
public class CallableFuture {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Task.Task01 task01 = new Task.Task01();
		Future<Integer> future = executor.submit(task01);  // 源码中其实是在submit(Callable<T> task)内部创建了一个RunnableFuture<T>接口实现类
		Task.Task02 task02 = new Task.Task02();
		Future<Integer> future2 = executor.submit(task02);

		/*
		 接下来就可以通过future来获取一些关于Task的运行信息了：
		 比如：future.get();来获取最后执行结果
		 future.isDown();来判断是否完成
		 等等...
		 **/
		System.out.println(future.get());
		System.out.println(future2.get());
		System.out.println("完成执行...");
		executor.shutdown();  // 关闭线程池
	}
}

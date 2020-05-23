package com.chengcheng.cases.current.callable;

import io.swagger.annotations.Api;

import java.util.concurrent.*;

@Api("Callable + FutureTask实现线程返回")
public class CallableFutureTask {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		//第一种方式
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Task.Task03 task03 = new Task.Task03();
		FutureTask<Integer> futureTask03 = new FutureTask<Integer>(task03);
		executor.submit(futureTask03);
		Task.Task04 task04 = new Task.Task04();
		FutureTask<Integer> futureTask04 = new FutureTask<Integer>(task04);
		executor.submit(futureTask04);

		//第二种方式
		/*
		 Task task = new Task();
		 FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		 Thread thread = new Thread(futureTask);
		 thread.start();
		 **/

		/*
		 接下来就可以通过futureTask来获取一些关于Task的运行信息了：
		 比如：futureTask.get();来获取最后执行结果
		 futureTask.isDown();来判断是否完成
		 等等...
		 **/
		System.out.println(futureTask03.get());
		System.out.println(futureTask04.get());
		System.out.println(futureTask03.get() + futureTask04.get());
		System.out.println("完成执行...");
		executor.shutdown();
	}
}


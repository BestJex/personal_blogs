package com.chengcheng.cases.current.forkjoin;

import io.swagger.annotations.Api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@Api("基于ForkJoinPool实现查找路径下的指定类型文件.")
public class UseForkJoinPool extends RecursiveAction {

	private File path;  // 当前任务需要搜寻的目录

	public UseForkJoinPool(File path) {
		this.path = path;
	}

	@Override
	protected void compute() {
		List<UseForkJoinPool> subTasks = new ArrayList<>();
		File[] files = path.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					subTasks.add(new UseForkJoinPool(file));
				} else {  // 文件
					if (file.getAbsolutePath().endsWith("txt")) {
						System.out.println("路径: " + file.getAbsolutePath());
					}
				}
			}
			if (!subTasks.isEmpty()) {
				for (UseForkJoinPool subTask : invokeAll(subTasks)) {
					subTask.join();  // 等待子任务执行完成.
				}
			}
		}
	}

	/* 测试 */
	public static void main(String[] args) {
		// 使用 ForkJoinPool 实例调度总任务
		ForkJoinPool pool = new ForkJoinPool();
		UseForkJoinPool task = new UseForkJoinPool(new File("F:/"));

		pool.execute(task);  // 异步调用
//		pool.invoke(task);  // 同步调用

		System.out.println("==== 执行搜索开始 ====");
		int otherWork = 0;
		for (int i = 0; i < 100; i++) {
			otherWork = otherWork + i;
		}
		System.out.println("Main Thread done sth......,otherWork="+otherWork);
		task.join();
		System.out.println("==== 执行搜索结束 ====");
	}
}

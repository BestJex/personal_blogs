package com.chengcheng.cases.current.threadpool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Api("线程池 - newFixedThreadPool")
@Repository
public class FixedThreadPool {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FixedThreadPool.class);
	private static int POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;  // 最优线程数
	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(POOL_SIZE);  // 线程池

	public FixedThreadPool() {  // 正式情况下构造方法应该设为私有
	}

	public FixedThreadPool(int pool_size) { // 正式情况下构造方法应该设为私有
		POOL_SIZE = pool_size;
	}

	public ExecutorService pool() {
		return fixedThreadPool;
	}

	@ApiOperation("线程池测试")
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						System.out.println(index);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	// 注意事项:
	// - newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	// - 获取当前时间: long start = System.currentTimeMillis();
	// - 关闭线程池: fixedThreadPool.shutdown();
	// - 统计所有人任务休眠的总时长: AtomicInteger count = new AtomicInteger(0);
	// - others...

}

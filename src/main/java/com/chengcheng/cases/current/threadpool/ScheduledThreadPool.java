package com.chengcheng.cases.current.threadpool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Api("定时执行周期线程池 - newScheduledThreadPool")
public class ScheduledThreadPool {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ScheduledThreadPool.class);
	private static int POOL_SIZE = 1;
	private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(POOL_SIZE);

	private static ScheduledThreadPool scheduled = new ScheduledThreadPool();

	private ScheduledThreadPool() {  // 使线程单例
	}

	// 调用实例
	public static ScheduledExecutorService getScheduledThreadPool() {
		return scheduled.scheduledThreadPool;
	}

	@ApiOperation("定时线程执行测试方法")
	public static void main(String[] args) {
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("定时线程启动输出");
			}
		}, 0, 1000, TimeUnit.MILLISECONDS);
	}

}

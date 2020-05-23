package com.chengcheng.cases.current.threadpool;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Api("Scheduled线程池测试类")
@RestController
public class ThreadScheduledRun {

	@RequestMapping(value = "scheduledThread", method = RequestMethod.GET)
	public void scheduledThread() {
		ScheduledExecutorService pool = ScheduledThreadPool.getScheduledThreadPool();
		pool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("delay 1 seconds");
			}
		}, 0, 1000, TimeUnit.MILLISECONDS);
	}

}

package com.chengcheng.cases.current.threadpool;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

@Api("使用线程池的多线程测试")
@RestController
public class ThreadRun {

	private static FixedThreadPool fixedThreadPool = new FixedThreadPool(20);

	@RequestMapping(value = "/attack", method = RequestMethod.GET)
	public void attack () {
		ExecutorService fixedPool = fixedThreadPool.pool();
		fixedPool.execute(new Runnable() {
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println("第一个线程");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		fixedPool.execute(new Runnable() {
			public void run() {
				try {
					Thread.sleep(500);
					System.out.println("第二个线程");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		fixedPool.execute(new Runnable() {
			public void run() {
				try {
					System.out.println("第三个线程");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

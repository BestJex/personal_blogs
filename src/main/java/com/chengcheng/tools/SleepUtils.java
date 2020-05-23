package com.chengcheng.tools;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.concurrent.TimeUnit;

@Api("线程休眠辅助工具类")
public class SleepUtils {

	@ApiOperation("按秒休眠")
	public static void sec(@ApiParam("秒数") int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@ApiOperation("按毫秒数睡眠")
	public static void ms(@ApiParam("毫秒数") int ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

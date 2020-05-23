package com.chengcheng.controller;


import com.chengcheng.service.HelloService;
import com.chengcheng.tools.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Api("服务测试")
@RestController
@RequestMapping(value = "test")
public class HelloController extends CommonController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HelloController.class);

	@ApiOperation("测试请求")
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String helloWorld() {
		return "Hello World ~";
	}

	@ApiOperation("测试mysql连接")
	@RequestMapping(value = "mysql", method = RequestMethod.GET)
	public String helloMysql() {

		return helloService.helloMysql();
	}

	@ApiOperation("测试PostgresSql")
	@RequestMapping(value = "postgres", method = RequestMethod.GET)
	public String helloPostgres() {

		return helloService.helloPostgres();
	}

	@ApiOperation("测试Oracle")
	@RequestMapping(value = "oracle", method = RequestMethod.GET)
	public String helloOracle() {

		return helloService.helloOracle();
	}

	@ApiOperation("测试Redis")
	@RequestMapping(value = "redis", method = RequestMethod.GET)
	public String helloRedis() {

		redisUtils.set("redisKey", "Hello Redis");
		return "";
	}

	@ApiOperation("测试Aop")
	@RequestMapping(value = "aop", method = RequestMethod.GET)
	public void aspect() {
		helloService.conference();
	}

	@ApiOperation("测试事务")
	@RequestMapping(value = "trans", method = RequestMethod.GET)
	public void Trans() {
		helloService.helloInsertTransMysql();
	}

}

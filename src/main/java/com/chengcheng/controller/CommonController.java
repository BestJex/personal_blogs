package com.chengcheng.controller;

import com.chengcheng.service.HelloService;
import com.chengcheng.service.WagesService;
import com.chengcheng.tools.RedisUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RestController;

@Api("Controller层公共类-父类")
@RestController
public class CommonController {

	@Autowired
	HelloService helloService;

	@Autowired
	RedisUtils redisUtils;

	@Autowired
	WagesService wagesService;

}

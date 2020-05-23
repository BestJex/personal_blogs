package com.chengcheng.controller;

import com.chengcheng.entity.hellomodel.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("ElasticSearch")
@RestController
@RequestMapping(value = "esTest")
public class HelloESController extends CommonController {

	// 参考地址: https://blog.csdn.net/chen_2890/article/details/83895646

	/* 测试es时将此处打开 */
/*	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@ApiOperation("创建索引，会根据Item类的@Document注解信息来创建")
	@RequestMapping(value = "es", method = RequestMethod.GET)
	public void testCreateIndex() {
		elasticsearchTemplate.createIndex(Item.class);
	}*/
}

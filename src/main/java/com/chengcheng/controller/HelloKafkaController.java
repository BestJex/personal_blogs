package com.chengcheng.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Kafka生产消费-测试")
@RequestMapping(value = "kafka")
public class HelloKafkaController {

	/*长时间连接不上,会报错 - 要测试kafka先连接上kafka, 再将下面注释内容打开.*/
	/*@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@GetMapping("/message/send")
	public boolean send(@RequestParam("message") String message) {
		kafkaTemplate.send("testTopic", message);
		return true;
	}

	@ApiOperation("测试Kafka")
	@KafkaListener(topics = "testTopic")
	public void onMessage(String message){
		//insertIntoDb(buffer);//这里为插入数据库代码
		System.out.println(message);
	}*/

}

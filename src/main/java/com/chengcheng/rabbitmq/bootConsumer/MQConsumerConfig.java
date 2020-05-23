package com.chengcheng.rabbitmq.bootConsumer;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Api("RabbitMQ - 消费端主配置类")
@Configuration
@ComponentScan({"com.chengcheng.rabbitmq.bootConsumer.*"})
public class MQConsumerConfig {

}

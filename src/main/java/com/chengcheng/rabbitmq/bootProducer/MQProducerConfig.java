package com.chengcheng.rabbitmq.bootProducer;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Api("RabbitMQ - 生产端主配置类")
@Configuration
@ComponentScan({"com.chengcheng.rabbitmq.bootProducer.*"})
public class MQProducerConfig {

}

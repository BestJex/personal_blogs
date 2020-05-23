package com.chengcheng.rabbitmq.bootConsumer;

import com.chengcheng.entity.casesmodel.rabbitmqModel.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

import java.util.Map;

/*使用时将 24 - 38 行, 50 - 66 行注释打开.*/
@Api("消费端")
@Component
public class RabbitReceiver {

	/*@ApiOperation("消费队列的数据")
	@RabbitHandler
	@RabbitListener(bindings = @QueueBinding(
			// 指定队列名称, 是否持久化
			value = @Queue(value = "queue-1", durable = "true"),
			// 指定路由名称, 是否持久化, 类型, 是否过滤, 指定路由key
			exchange = @Exchange(value = "exchange-1", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"), key = "springboot.*")
	)
	public void onMessage(Message message, Channel channel) throws Exception {
		System.out.println("消费端PayLoad: " + message.getPayload());  // 消费端收到消费体内容
		Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
		if (deliveryTag != null) {
			channel.basicAck(deliveryTag, false);  // 手工ACK
		}
	}*/

	/* 动态获取参数方式 */
/*	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(value = "${spring.rabbitmq.listener.order.queue.name}",
					durable="${spring.rabbitmq.listener.order.queue.durable}"),
			exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange.name}",
					durable="${spring.rabbitmq.listener.order.exchange.durable}",
					type= "${spring.rabbitmq.listener.order.exchange.type}",
					ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions}"),
			key = "${spring.rabbitmq.listener.order.key}")
	)*/
	/*@ApiOperation("以Java对象的格式消费队列的数据")
	@RabbitHandler
	@RabbitListener(bindings = @QueueBinding(
			// 指定队列名称, 是否持久化
			value = @Queue(value = "queue-2",
					durable = "true"),
			// 指定路由名称, 是否持久化, 类型, 是否过滤, 指定路由key
			exchange = @Exchange(value = "exchange-2", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"), key = "springboot.*")
	)
	// 此处将message拆开 拆成了@Payload和@Headers
	public void onOrderMessage(@Payload Order order,  // @Payload 注解用于指定实际的消息体内容
	                           Channel channel,
	                           @Headers Map<String, Object> headers) throws Exception {  // @Headers
		System.out.println("消费端Order: " + order.getId() + " || " + order.getName());
		Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
		channel.basicAck(deliveryTag, false);  // 手工ACK
	}*/

}

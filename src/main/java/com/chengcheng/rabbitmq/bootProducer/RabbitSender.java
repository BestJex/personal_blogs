package com.chengcheng.rabbitmq.bootProducer;

import com.chengcheng.entity.casesmodel.rabbitmqModel.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;  // 注意这个不要引错包
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;  // 注意引这个包
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;  // 注意引这个包
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api("生产端")
@RestController
public class RabbitSender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@ApiOperation("生产测试接口")
	@RequestMapping(value = "rabbitmqTest01", method = RequestMethod.GET)
	public void test01() throws Exception {
		Map<String, Object> properties = new HashMap<>();
		properties.put("number", "12345");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		properties.put("send_time", simpleDateFormat.format(new Date()));
		send("Hello RabbitMQ For Spring Boot!", properties);
	}

	@ApiOperation("对象生产测试接口")
	@RequestMapping(value = "rabbitmqTest02", method = RequestMethod.GET)
	public void test02() throws Exception {
		Order order = new Order("001","张宜成");
		sendOrder(order);
	}

	@ApiOperation("发送消息")
	public void send(Object message, Map<String, Object> properties) throws Exception {
		MessageHeaders mhs = new MessageHeaders(properties);
		Message msg = MessageBuilder.createMessage(message, mhs);
		rabbitTemplate.setConfirmCallback(confirmCallback);  // ----  1
		rabbitTemplate.setReturnCallback(returnCallback);  // ----  2
		CorrelationData cd = new CorrelationData("1234567890");  // 此处是伪代码, 正式时使用id + 时间戳,全局唯一
		rabbitTemplate.convertAndSend("exchange-1", "springboot.hello", msg);
	}

	@ApiOperation("发送消息方法调用: 构建自定义对象消息")
	private void sendOrder(Order order) throws Exception {
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.setReturnCallback(returnCallback);
		//id + 时间戳 全局唯一
		CorrelationData correlationData = new CorrelationData("0987654321");
		rabbitTemplate.convertAndSend("exchange-2", "springboot.def", order, correlationData);
	}

	/**
	 * 实现一个监听器用户监听Broker端给我们返回的确认请求  --- 1
	 * 注意一点, 在发消息的时候对template进行配置mandatory=true保证监听有效
	 * 回调函数: confirm确认
	 */
	private final ConfirmCallback confirmCallback = new ConfirmCallback() {

		@Override
		public void confirm(CorrelationData correlationData, boolean ack, String cause) {
			System.out.println("correlationData: " + correlationData);
			System.out.println("ACK: " + ack);
			// 如果ack为false则进行补偿措施
			if (!ack) {
				System.out.println("ACK返回的为False, 异常处理...");
			}
			// 处理可靠性投递....
		}
	};

	/**
	 *  保证消息对Broker端是可达的,如果出现路由键不可达的情况,则使用监听器对不可达的消息进行后续的处理, 保证消息的路由成功   --- 2
	 *  回调函数: return返回 如果路由不存在调用
	 */
	private final ReturnCallback returnCallback = new ReturnCallback() {
		@Override
		public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
		                            String exchange, String routingKey) {
			System.out.println("return exchange: " + exchange + ", routingKey: "
					+ routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
		}
	};

}

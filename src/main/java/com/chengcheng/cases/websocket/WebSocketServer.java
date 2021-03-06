package com.chengcheng.cases.websocket;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


@Api("WebSocket操作")
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {

	private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);


	private static int onlineCount = 0;  //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();  //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private Session session;  //与某个客户端的连接会话，需要通过它来给客户端发送数据
	private String sid = "";    //接收sid

	@ApiOperation("连接建立成功调用的方法")
	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid) {
		this.session = session;
		webSocketSet.add(this);     //加入set中
		addOnlineCount();           //在线数加1
		log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + getOnlineCount());
		this.sid = sid;
		try {
			sendMessage("连接成功");
		} catch (IOException e) {
			log.error("WebSocket IO异常");
		}
	}

	@ApiOperation("收到客户端消息后调用的方法")
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("收到来自窗口" + sid + "的信息:" + message);
		//群发消息
		for (WebSocketServer item : webSocketSet) {
			try {
				item.sendMessage(message);  // message 客户端发送过来的消息
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@ApiOperation("连接关闭调用的方法")
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);  //从set中删除
		subOnlineCount();           //在线数减1
		log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	@ApiOperation("发生错误")
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误");
		error.printStackTrace();
	}

	@ApiOperation("实现服务器主动推送")
	public void sendMessage(String message) throws IOException {
		Integer i = 0;
		while (true) {
			this.session.getBasicRemote().sendText("推送消息" + i);
			System.out.println("后台推送消息中..." + i);
			i++;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@ApiOperation("群发消息")
	public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
		log.info("推送消息到窗口" + sid + "，推送内容:" + message);
		for (WebSocketServer item : webSocketSet) {
			try {
				//这里可以设定只推送给这个sid的，为null则全部推送
				if (sid == null) {
					item.sendMessage(message);
				} else if (item.sid.equals(sid)) {
					item.sendMessage(message);
				}
			} catch (IOException e) {
				continue;
			}
		}
	}

	@ApiOperation("获取当前在线人数")
	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	@ApiOperation("当前在线人数 + 1")
	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	@ApiOperation("当前在线人数 - 1")
	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}
}


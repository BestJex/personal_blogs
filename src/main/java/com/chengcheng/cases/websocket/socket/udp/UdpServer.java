package com.chengcheng.cases.websocket.socket.udp;

import io.swagger.annotations.Api;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Api("UDP服务端")
public class UdpServer {

	public static void main(String[] args) throws IOException {
		byte buf[] = new byte[1024];  // 分配包裹空间, 用于存储数据
		DatagramPacket dp = new DatagramPacket(buf, buf.length);  // 用于接收数据, 存储再buf中
		DatagramSocket ds = new DatagramSocket(5678);

		while (true) {
			ds.receive(dp);
			ByteArrayInputStream bais = new ByteArrayInputStream(buf);
			DataInputStream dis = new DataInputStream(bais);
			System.out.println("接收到数据: " + dis.readLong());
		}
	}

}

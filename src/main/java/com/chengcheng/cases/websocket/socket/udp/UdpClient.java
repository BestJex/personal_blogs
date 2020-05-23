package com.chengcheng.cases.websocket.socket.udp;

import io.swagger.annotations.Api;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

@Api("UDP客户端")
public class UdpClient {

	public static void main(String[] args) throws IOException {
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 5678);
		byte[] buf = (new String("Hello !")).getBytes();
		DatagramPacket dp = new DatagramPacket(buf, buf.length, isa);  // 每一个包裹都需要包含server端的地址
		DatagramSocket ds = new DatagramSocket(9999);

		long n = 10000L;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeLong(n);  // 传输基础数据类型
		System.out.println("已发送: " + n);

		byte[] buf2 = baos.toByteArray();
		dp = new DatagramPacket(buf2, buf2.length, isa);
		ds.send(dp);
		ds.close();
	}

}

package com.chengcheng.cases.websocket.socket.tcp;

import io.swagger.annotations.Api;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Api("TCP服务端")
public class TcpServer {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(6666);  // 建立通讯需要先执行server，需要指定端口号
			while (true) {
				Socket s = ss.accept();  // 接受client端的连接申请，返回client端的socket
				System.out.println("A client connected!");
				DataInputStream dis = new DataInputStream(s.getInputStream());  // 建立server端的输入管道，即client端的输出管道
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());  // 建立server端的输出管道
				String str = null;
				if ((str = dis.readUTF()) != null) {
					System.out.println(str);
					//readUTF为阻塞式方法，通信过程中需要等待client端
					System.out.println("from " + s.getInetAddress() + ", port #" + s.getPort());
				}
				dos.writeUTF("Hello, " + s.getInetAddress() + ", port#" + s.getPort());
				dis.close();
				dos.close();
				s.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("Server excpetion!");
		}
	}

}

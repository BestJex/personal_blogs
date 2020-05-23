package com.chengcheng.cases.websocket.socket.tcp;

import io.swagger.annotations.Api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

@Api("TCP客户端")
public class TcpClient {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 6666);
			OutputStream os = s.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);  //拿到client端输出管道
			dos.writeUTF("Hello, server!");  //包装数据流管道
			DataInputStream dis = new DataInputStream(s.getInputStream());
			//建立client端的输入管道
			System.out.println(dis.readUTF());
			dos.flush();
			dos.close();
			dis.close();
			s.close();
		} catch (ConnectException ex) {
			ex.printStackTrace();
			System.out.println("Connection fails!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}

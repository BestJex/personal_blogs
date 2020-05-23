package com.chengcheng.cases.type.ioflow.character_;

import io.swagger.annotations.Api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Api("高效缓冲字符输入 - BufferedReader - 04")
public class BufferReader {

	public static void main(String[] args) {
		BufferedReader reader = null;
		try {
			// 创建一个高效缓存字符输入流对象
			reader = new BufferedReader(new FileReader("./src/main/resources/templates/download/txt/BufferedWriterTest.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);  // 打印
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭外层对象资源时, 内层资源对象会被自动关闭
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

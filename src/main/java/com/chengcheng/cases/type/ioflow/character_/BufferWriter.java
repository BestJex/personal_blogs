package com.chengcheng.cases.type.ioflow.character_;

import io.swagger.annotations.Api;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Api("高效缓冲字符输出 - BufferedWriter - 05")
public class BufferWriter {

	public static void main(String[] args) {
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter("./src/main/resources/templates/download/txt/BufferedWriterTest.txt"));
			writer.write("hello ZhangYiCheng!!!!");  // 写入一行数据
			writer.newLine();  // 换行
			writer.write("hello LangQian!!!!");
			writer.flush();  // flush
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 资源关闭
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

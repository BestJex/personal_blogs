package com.chengcheng.cases.type.ioflow.character_;

import io.swagger.annotations.Api;

import java.io.*;
import java.io.FileWriter;

@Api("文件拷贝案例案例 - 03")
public class FileReaderWriter {

	public static void main(String[] args) {
		Reader reader = null;
		Writer writer = null;
		try {
			reader = new java.io.FileReader("./src/main/resources/templates/download/txt/FileWriterTest.txt");
			writer = new FileWriter("./src/main/resources/templates/download/txt/FileReaderWriter.txt");
			char[] cs = new char[1024];
			int len = -1;
			while ((len = reader.read(cs)) != -1) {
				writer.write(cs, 0, len);  // 将输入流读取到的数据写入字符输出流
			}
			writer.flush();  // 文件flush
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}

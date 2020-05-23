package com.chengcheng.cases.type.ioflow.character_;

import io.swagger.annotations.Api;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;

@Api("高效读写拷贝文件案例 - 06")
public class BufferReaderWriter {

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader("./src/main/resources/templates/download/txt/BufferedWriterTest.txt"));
			writer = new BufferedWriter(new FileWriter("./src/main/resources/templates/download/txt/BufferedWriterCopy.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// 把读入的这一行数据写入到高效缓冲字符输出流里面
				writer.write(line);
				writer.newLine();
				writer.flush();
			}
		} catch (IOException e) {
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

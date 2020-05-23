package com.chengcheng.cases.type.ioflow.character_;

import io.swagger.annotations.Api;

import java.io.IOException;
import java.io.Reader;

@Api("文件读出 - FileReader - 02")
public class FileReader {

	public static void main(String[] args) {
		Reader reader = null;
		try {
			// 创建输入流对象FileReader
			reader = new java.io.FileReader("./src/main/resources/templates/download/txt/FileWriterTest.txt");
			// 定义一个数组
			char[] cs = new char[5];  // 数组大小可根据实际情况而变动
			// 向字符数组中填数据
			int len = -1;
			while ((len = reader.read(cs)) != -1) {
				// 把字符数组数组变成字符串
				String str = new String(cs, 0, len);
				System.out.print(str);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (reader != null) {
				try {
					reader.close();  // 关闭资源
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

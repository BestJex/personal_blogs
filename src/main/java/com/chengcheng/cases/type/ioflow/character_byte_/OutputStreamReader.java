package com.chengcheng.cases.type.ioflow.character_byte_;

import io.swagger.annotations.Api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Api("字符流转换字节流的桥梁的对象 -Reader操作Stream - 02")
public class OutputStreamReader {

	public static void main(String[] args) {
		// 创建字符流转换字节流的桥梁的都西昂
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream("./src/main/resources/templates/download/txt/OutputStreamWriterTest.txt"), "UTF-8");
			char[] cs = new char[1024];
			int len = reader.read(cs);
			System.out.println(new String(cs, 0, len));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

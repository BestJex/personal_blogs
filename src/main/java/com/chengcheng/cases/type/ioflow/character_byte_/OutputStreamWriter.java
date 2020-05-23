package com.chengcheng.cases.type.ioflow.character_byte_;

import io.swagger.annotations.Api;

import java.io.FileOutputStream;
import java.io.IOException;

@Api("字符流转换字节流的桥梁的对象 - Reader操作Stream - 01")
public class OutputStreamWriter {

	public static void main(String[] args) {
		// 创建字符流转换字节流的桥梁的都西昂
		java.io.OutputStreamWriter ow = null;
		try {
			ow = new java.io.OutputStreamWriter(new FileOutputStream("./src/main/resources/templates/download/txt/OutputStreamWriterTest.txt"), "UTF-8");
			ow.write("hello Reader操作Stream!!!");
			ow.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ow != null) {
					ow.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

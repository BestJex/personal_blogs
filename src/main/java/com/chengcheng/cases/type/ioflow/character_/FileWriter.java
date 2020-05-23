package com.chengcheng.cases.type.ioflow.character_;

import io.swagger.annotations.Api;

import java.io.IOException;
import java.io.Writer;

@Api("文件写入 - FileWriter - 01")
public class FileWriter {

	public static void main(String[] args) {
		Writer writer = null;
		try {
			writer = new java.io.FileWriter("./src/main/resources/templates/download/txt/FileWriterTest.txt");  // 后面加true,是追加
			writer.write("Hello World!!!!!!!"); // 写入 || 如果文件量过大,要设置定期flush || 换行 -> windows:\r\n Linux:\n || 有添加数组的方法
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 判断writer不是null, 防止空指针异常
			if (writer != null) {
				try {
					writer.close();  // 在关闭前会作flush操作. 相当于执行了writer.flush
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

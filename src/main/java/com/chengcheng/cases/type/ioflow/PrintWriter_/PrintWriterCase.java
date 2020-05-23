package com.chengcheng.cases.type.ioflow.PrintWriter_;

import io.swagger.annotations.Api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

@Api("字符打印流 - 只有输出没有输入")
/*
 *  特点:
 *      1. 可以打印各种数据类型
 *      2. 封装了字符输出流, 还可以字符流和字节流的转换
 *      3. 可以使用自动刷新, 则只有在待用println, printf,或format的其中一个方法时才可能完成此操作.
 *      4. 可以直接向文件中写数据
 */
public class PrintWriterCase {

	public static void main(String[] args) {
		PrintWriter pw = null;
		BufferedReader br = null;
		try {

			// 1. 写操作.
			/*pw = new PrintWriter("./src/main/resources/templates/download/txt/PrintWriterTest.txt");
			pw.println("1");
			pw.println(11111);*/

			// 2. 打印到控制台
			/*br = new BufferedReader(new FileReader("./src/main/resources/templates/download/txt/PrintWriterTest.txt"));
			pw = new PrintWriter(System.out); // 将读取的内容打印到控制台
			String line = null;
			while ((line = br.readLine()) != null) {
				pw.println(line);
				pw.flush();
			}*/

			// 3. copy文件
			br = new BufferedReader(new FileReader("./src/main/resources/templates/download/txt/PrintWriterTest.txt"));
			pw = new PrintWriter("./src/main/resources/templates/download/txt/PrintWriterCopy.txt"); // 将读取的内容打印到控制台
			String line = null;
			while ((line = br.readLine()) != null) {
				pw.println(line);
				pw.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

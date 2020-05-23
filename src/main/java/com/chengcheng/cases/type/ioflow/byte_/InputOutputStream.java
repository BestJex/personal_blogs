package com.chengcheng.cases.type.ioflow.byte_;

import io.swagger.annotations.Api;

import java.io.*;

@Api("二进制文件拷贝 - 01")
public class InputOutputStream {

	public static void main(String[] args) {
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream("./src/main/resources/templates/download/img/zhengshu.jpg");
			out = new FileOutputStream("./src/main/resources/templates/download/img/zhengshuCopy.jpg");
			byte[] bs = new byte[1024];
			int len = -1;
			while ((len = in.read(bs)) != -1) {
				out.write(bs, 0, len);  // 把读取的数据写入字符输出流
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

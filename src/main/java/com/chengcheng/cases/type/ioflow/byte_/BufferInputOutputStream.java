package com.chengcheng.cases.type.ioflow.byte_;

import io.swagger.annotations.Api;

import java.io.*;

@Api("高效缓冲二进制文件拷贝 - 02")
public class BufferInputOutputStream {

	public static void main(String[] args) {
		// 定义一个高效缓存字节流
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream("./src/main/resources/templates/download/img/zhengshu.jpg"));
			out = new BufferedOutputStream(new FileOutputStream("./src/main/resources/templates/download/img/zhengshuBufferedCopy.jpg"));
			byte[] bs = new byte[1024];
			int len = 1;
			while ((len = in.read(bs)) != -1) {
				out.write(bs, 0, len);
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

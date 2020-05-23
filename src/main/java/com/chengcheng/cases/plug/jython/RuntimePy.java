package com.chengcheng.cases.plug.jython;

import io.swagger.annotations.Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Api("使用Java类来调用Python - 存在问题")
public class RuntimePy {

	public static void main(String[] args) {
		Process proc;
		try {
			proc = Runtime.getRuntime().exec("python D:\\IntelliJ IDEA\\ideaone\\personal-blogs\\src\\main\\java\\com\\chengcheng\\cases\\plug\\jython\\pyshell\\runtime_one.py");// 执行py文件
			//用输入输出流来截取结果
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			in.close();
			proc.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}

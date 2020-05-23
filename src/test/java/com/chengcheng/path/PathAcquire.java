package com.chengcheng.path;

import com.chengcheng.PersonalBlogApplicationTests;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PathAcquire extends PersonalBlogApplicationTests {


	@Test
	public void showURL() throws IOException {

		// 第一种：获取类加载的根路径
		File f = new File(this.getClass().getResource("/").getPath());
		System.out.println("1. 获取类加载的根路径: " + f);

		// 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println("2. 获取当前类的所在工程路径: " + f2);

		// 第二种：获取项目路径
		File directory = new File("");// 参数为空
		String courseFile = directory.getCanonicalPath();
		System.out.println("3. 获取项目路径: " + courseFile);

		// 第三种：
		System.out.println("4. 获取项目路径: " + System.getProperty("user.dir"));

		// 第四种：
		URL xmlpath = this.getClass().getClassLoader().getResource("");
		System.out.println("5. file路径: " + xmlpath);



	}
}

package com.chengcheng;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration  //由于是Web项目，Junit需要模拟ServletContext
@SpringBootTest(classes = PersonalBlogApplicationTests.class)
public class PersonalBlogApplicationTests {

	@Test
	public void contextLoads() {

	}

	@Before
	public void init() {
		System.out.println("--- 开始测试 ---");
	}

	@After
	public void after() {
		System.out.println("--- 测试结束 ---");
	}

}

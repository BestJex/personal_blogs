package com.chengcheng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})  // 不加载默认数据源, 否则搭建项目初期无法启动
@MapperScan({"com.chengcheng.dao.mysqlDao", "com.chengcheng.dao.postgresDao", "com.chengcheng.dao.oracleDao"})
@EnableScheduling  // 开启定时任务
@EnableAspectJAutoProxy(proxyTargetClass = true)  // 开启代理  默认为false, 表示使用jdk动态代理织入增强; true，表示使用Cglib动态代理技术织入增强;
@EnableTransactionManagement  // 开启事务注解, 备注: 在大多数情况下, 不是必须的.
//@EnableElasticsearchRepositories(basePackages = "")  // ES启动
@SpringBootApplication
public class PersonalBlogApplication {

	public static void main(String[] args) {
		System.setProperty("es.set.netty.runtime.available.processors", "false");  // es配置, 目前存在问题.
		SpringApplication.run(PersonalBlogApplication.class, args);
	}

}

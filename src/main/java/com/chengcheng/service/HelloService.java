package com.chengcheng.service;

public interface HelloService {

	public String helloMysql();

	public String helloOracle();

	public String helloPostgres();

	void conference();  // 切面编程测试

	void helloInsertTransMysql();  // 事务测试

}

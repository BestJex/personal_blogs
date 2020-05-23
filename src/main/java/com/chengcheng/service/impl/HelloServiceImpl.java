package com.chengcheng.service.impl;

import com.chengcheng.dao.mysqlDao.HelloMysqlDao;
import com.chengcheng.dao.oracleDao.HelloOracleDao;
import com.chengcheng.dao.postgresDao.HelloPostgresDao;
import com.chengcheng.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HelloServiceImpl implements HelloService {

	@Autowired
	HelloMysqlDao helloMysqlDao;

	@Autowired
	HelloOracleDao helloOracleDao;

	@Autowired
	HelloPostgresDao helloPostgresDao;

	@Override
	public String helloMysql() {

		return helloMysqlDao.helloMysql();
	}

	@Override
	public String helloOracle() {

		return helloOracleDao.helloOracle();
	}

	@Override
	public String helloPostgres() {

		return helloPostgresDao.helloPostgres();
	}

	@Override
	public void conference() {
		System.out.println("开会......");
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void helloInsertTransMysql() {
		try {
			helloMysqlDao.insertHelloMysql();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发生了异常, 开始回滚...");
		}
		System.out.println("顺利执行完毕...");
	}

}

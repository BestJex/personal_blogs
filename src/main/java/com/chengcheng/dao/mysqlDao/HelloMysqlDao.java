package com.chengcheng.dao.mysqlDao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloMysqlDao {

	public String helloMysql();

	public void insertHelloMysql();

}

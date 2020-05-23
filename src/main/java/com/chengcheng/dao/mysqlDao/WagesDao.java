package com.chengcheng.dao.mysqlDao;

import com.alibaba.fastjson.JSONObject;
import com.chengcheng.entity.Wages;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface WagesDao {

	// 基本数据展示
	public ArrayList<Wages> selectWagesAll(@Param("wagesDate")String wagesDate);

	// 添加
	public Object addOneItem(@Param("insertTime") String insertTime,
	                         @Param("updateTime") String updateTime,
	                         @Param("wagesDate") String wagesDate,
	                         @Param("money") int money);

	// 更新
	public Object updateOneItem(@Param("insertTime") String insertTime,
	                            @Param("updateTime") String updateTime,
	                            @Param("wagesDate") String wagesDate,
	                            @Param("money") int money);

	// 日志插入
	public void addOneLog(@Param("insertTime") String insertTime,
	                      @Param("user") String user,
	                      @Param("status") String status,
	                      @Param("msg") String msg);

}

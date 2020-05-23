package com.chengcheng.service;

import com.alibaba.fastjson.JSONObject;

public interface WagesService {

	// 基本数据展示
	public JSONObject selectWagesAll(String wagesDate);

	// 添加
	public Object addOneItem(String insertTime, String updateTime, String wagesDate, int money);

	// 更新
	public Object updateOneItem(String insertTime, String updateTime, String wagesDate, int money);

	// 日志插入
	public void addOneLog(String insertTime, String user, String status, String msg);


}

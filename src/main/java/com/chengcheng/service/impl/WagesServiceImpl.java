package com.chengcheng.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chengcheng.dao.mysqlDao.WagesDao;
import com.chengcheng.entity.Wages;
import com.chengcheng.service.WagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WagesServiceImpl implements WagesService {

	@Autowired
	WagesDao wagesDao;

	/**
	 * 基本数据展示
	 */
	@Override
	public JSONObject selectWagesAll(String wagesDate) {

		ArrayList<Wages> result = wagesDao.selectWagesAll(wagesDate);
		JSONObject allObj = new JSONObject();
		JSONObject total = new JSONObject();
		JSONObject zycObj = new JSONObject();
		JSONObject lqObj = new JSONObject();
		JSONArray zycArray = new JSONArray();
		JSONArray lqArray = new JSONArray();

		// 判断Total
		if (result.size() == 0) {
			total.put("zyc", 0);
			total.put("lq", 0);
			for (int i = 0; i < 12; i++) {
				zycArray.add(0);
				lqArray.add(0);
			}
		} else {
			int j = 1;
			for (Wages wages : result) {
				if (("0".equals(wages.getDate()))) {
					Boolean bol = (Boolean) ((!(total.containsKey("zyc"))) ? total.put("zyc", wages.getMany()) : total.put("lq", wages.getMany()));
				} else {
					// 数据格式化
					int mon = 0;
					String month = wages.getDate().split("-")[1];
					mon = (month.indexOf("0") == 0) ? Integer.parseInt(month.substring(1)) : Integer.parseInt(month);
					Boolean bol = (Boolean) ((Boolean) ("1".equals(wages.getUsr())) ? zycObj.put(mon + "", wages.getMany()) : lqObj.put(mon + "", wages.getMany()));
				}
			}

			// 将数据整合
			for (int i = 0; i < 12; i++) {
				zycArray.add(zycObj.getOrDefault(i + 1 + "", 0));
				lqArray.add(lqObj.getOrDefault(i + 1 + "", 0));
			}
		}

		allObj.put("year", wagesDate);
		allObj.put("total", total);
		allObj.put("zyc", zycArray);
		allObj.put("lq", lqArray);

		return allObj;
	}

	/**
	 * 添加基本数据
	 */
	@Override
	public Object addOneItem(String insertTime, String updateTime, String wagesDate, int money) {
		return null;
	}

	/**
	 * 更新基本数据
	 */
	@Override
	public Object updateOneItem(String insertTime, String updateTime, String wagesDate, int money) {
		return null;
	}

	/**
	 * 行为记录日志
	 */
	@Override
	public void addOneLog(String insertTime, String user, String status, String msg) {

	}

}

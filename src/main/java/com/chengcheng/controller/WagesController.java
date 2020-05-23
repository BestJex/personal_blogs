package com.chengcheng.controller;

import com.alibaba.fastjson.JSONObject;
import com.chengcheng.service.WagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("薪资统计")
@RestController
public class WagesController extends CommonController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WagesController.class);

	@ApiOperation("基本数据展示")
	@RequestMapping(value = "selectWagesAll", method = RequestMethod.GET)
	public JSONObject selectWagesAll(@RequestParam("wagesDate") String wagesDate) {

		JSONObject result = wagesService.selectWagesAll(wagesDate);
		logger.info(result.toJSONString());

		return result;
	}

	@ApiOperation("添加基本数据")
	@RequestMapping(value = "addOneItem", method = RequestMethod.GET)
	public String addOneItem(@RequestParam("wagesDate") String wagesDate,
	                         @RequestParam("money") int money) {

		return null;
	}

	@ApiOperation("更新基本数据")
	@RequestMapping(value = "updateOneItem", method = RequestMethod.GET)
	public String updateOneItem(@RequestParam("wagesDate") String wagesDate,
	                            @RequestParam("money") int money) {

		return null;
	}

	@ApiOperation("记录行为日志")
	@RequestMapping(value = "addOneLog", method = RequestMethod.GET)
	public void addOneLog() {


	}


}

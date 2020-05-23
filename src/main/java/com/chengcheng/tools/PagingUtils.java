package com.chengcheng.tools;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api("分页测试")
@RestController
public class PagingUtils {

	@ApiOperation("分页测试")
	@PostMapping("getPageing")
	public String getPaging (Integer pageSize,Integer pageNumber) {  // pageSize: 每页的条数, pageNumber: 页数

		// 分页
		if (pageNumber == null) {
			pageNumber = 1;
		}

		PageHelper.startPage(pageNumber, pageSize);
		List<Map<String,Object>> userList = null;  //  正常用法: testPaging.pagingSelect(pageSize, pageNumber)
		System.out.println(userList);

		return null;
	}
}
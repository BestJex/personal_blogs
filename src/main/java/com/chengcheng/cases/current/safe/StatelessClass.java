package com.chengcheng.cases.current.safe;

import com.chengcheng.entity.casesmodel.currentModel.UserVo;
import io.swagger.annotations.Api;

@Api("无状态的类 - 并发安全")
public class StatelessClass {

	public int service(int a, int b) {
		return a + b;
	}

	public void serviceUser(UserVo user) {
		// do sth user ..
	}

}

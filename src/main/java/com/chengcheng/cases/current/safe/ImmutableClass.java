package com.chengcheng.cases.current.safe;

import com.chengcheng.entity.casesmodel.currentModel.UserVo;
import io.swagger.annotations.Api;

@Api("类不可变 - 不安全")
public class ImmutableClass {

	private final int a;
	private final int b;
	private final UserVo user = new UserVo();  // 不安全

	public int getA() {
		return a;
	}

	public UserVo getUser() {
		return user;
	}

	public ImmutableClass(int a) {
		this.a = a;
		this.b = a;
	}

	public static class User {
		private int age;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}

}

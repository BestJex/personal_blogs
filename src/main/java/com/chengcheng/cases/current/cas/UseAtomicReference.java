package com.chengcheng.cases.current.cas;

import io.swagger.annotations.Api;

import java.util.concurrent.atomic.AtomicReference;

@Api("CAS原子操作 - AtomicReference")
public class UseAtomicReference {

	static AtomicReference<UserInfo> atomicUserRef;

	/* 定义一个实体类 */
	static class UserInfo {
		private volatile String name;
		private int age;
		public UserInfo(String name, int age) {
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public int getAge() {
			return age;
		}

		@Override
		public String toString() {
			return "UserInfo{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}

	/* 主函数测试类 */
	public static void main(String[] args) {
		UserInfo user = new UserInfo("Mark", 15);  // 要修改地实体的实例
		atomicUserRef = new AtomicReference<>(user);
		UserInfo updateUser = new UserInfo("Bill", 17);
		atomicUserRef.compareAndSet(user, updateUser);

		System.out.println(atomicUserRef.get());
		System.out.println(user);
	}

}

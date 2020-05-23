package com.chengcheng.cases.type.enumtype;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("自定义扩充枚举")
public enum EnumType02 {

	MONDAY(1, "星期一"), THUSDAY(2, "星期二");//这个后面必须有分号

	private int code;
	private String name;

	private EnumType02(int code, String name) {
		this.code = code;
		this.name = name();
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiOperation("测试")
	public static void main(String[] args) {
		System.out.println(EnumType02.MONDAY.getCode());
		System.out.println(EnumType02.MONDAY.getName());
		System.out.println(EnumType02.THUSDAY.getCode());
		System.out.println(EnumType02.THUSDAY.getName());
		System.out.println(EnumType02.THUSDAY);
	}

}

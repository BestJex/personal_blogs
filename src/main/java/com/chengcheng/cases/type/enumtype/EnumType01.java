package com.chengcheng.cases.type.enumtype;

import io.swagger.annotations.Api;

@Api("枚举类型")
public class EnumType01 {

	private String name;
	private int age;
	private XingBie xb;

	public EnumType01() {

	}

	public EnumType01(String name, int age, XingBie xb) {
		this.name = name;
		this.age = age;
		this.xb = xb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public XingBie getXb() {
		return xb;
	}

	public void setXb(XingBie xb) {
		this.xb = xb;
	}

	@Override
	public String toString() {
		return "EnumType{" +
				"name='" + name + '\'' +
				", age=" + age +
				", xb=" + xb +
				'}';
	}

	@Api("定义枚举类型")
	private enum  XingBie {
		男,女
	}

	public static void main(String[] args) {
		EnumType01 enumType = new EnumType01();
		enumType.setName("张宜成");
		enumType.setAge(13);
		enumType.setXb(XingBie.男);
		System.out.println(enumType.toString());
	}

}

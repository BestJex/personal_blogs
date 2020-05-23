package com.chengcheng.entity.casesmodel.clazzModel;

import io.swagger.annotations.Api;

@Api("类反射Model类")
public class ClazzBean {
	private String prop1;
	private Integer prop2;
	private Double prop3;

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public Integer getProp2() {
		return prop2;
	}

	public void setProp2(Integer prop2) {
		this.prop2 = prop2;
	}

	public Double getProp3() {
		return prop3;
	}

	public void setProp3(Double prop3) {
		this.prop3 = prop3;
	}
}

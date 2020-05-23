package com.chengcheng.entity;

import io.swagger.annotations.ApiModel;

@ApiModel("薪资类")
public class Wages {

	private String usr;
	private String date;
	private int many;

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public int getMany() {
		return many;
	}

	public void setMany(int many) {
		this.many = many;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Wages{" +
				"usr='" + usr + '\'' +
				", date='" + date + '\'' +
				", many=" + many +
				'}';
	}
}

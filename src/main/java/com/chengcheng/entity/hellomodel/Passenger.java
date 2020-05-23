package com.chengcheng.entity.hellomodel;

import io.swagger.annotations.ApiModel;
import org.springframework.data.mongodb.core.mapping.Document;

@ApiModel("MongoDB-测试类")
@Document(collection = "passenger")  // @Document的collection属性设置的是在mongo库中的集合名称
public class Passenger {

	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

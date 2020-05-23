package com.chengcheng.controller;

import com.chengcheng.entity.hellomodel.Passenger;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("MongoDB-CURD-测试")
@RestController
@RequestMapping(value = "testMongo")
public class HelloMongoController {

	@Autowired
	MongoTemplate mongoTemplate;

	@ApiOperation("插入操作")
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		Passenger passenger = new Passenger();
		passenger.setName("hello");
		passenger.setPassword("world1");
		passenger = mongoTemplate.insert(passenger);
		if (passenger != null) {
			return "success";
		} else {
			return "false";
		}
	}

	@ApiOperation("查询操作")
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query() {
		Query query = Query.query(Criteria.where("name").is("hello").and("password").is("world1"));
		List<Passenger> passengers = mongoTemplate.find(query, Passenger.class);
		return passengers.size() + "";
	}

	@ApiOperation("更新操作")
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update() {
		Query query = Query.query(Criteria.where("password").is("world1"));
		Update update = new Update();
		update.set("name", "world2");
		update.set("password", "world2");
		UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Passenger.class);
		return "success";
	}

	@ApiOperation("删除操作")
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove() {
		Query query = Query.query(Criteria.where("password").is("world1"));
		DeleteResult remove = mongoTemplate.remove(query, Passenger.class);
		return "success";
	}

}

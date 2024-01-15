package com.test.mongodb;

import com.achilles.server.entity.User;
import com.achilles.tool.date.DateUtil;
import com.achilles.tool.json.JsonUtil;
import com.test.BaseTest;
import com.test.mongodb.model.MongoDemo;
import org.bson.Document;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

public class MongodbQueryTest extends BaseTest {


    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void find() {
        List<User> users = mongoTemplate.findAll(User.class);
        users.forEach(user -> {
            System.out.println(JsonUtil.toJson(user));
        });
    }

    @Test
    public void findById() {
        User user = mongoTemplate.findById(1,User.class);
        System.out.println(JsonUtil.toJson(user));
    }

    @Test
    public void findOne() {
        User user = mongoTemplate.findOne(new Query(Criteria.where("id").is(1L)), User.class);
        System.out.println(JsonUtil.toJson(user));
    }

    @Test
    public void findGtList() {
        List<User> users = mongoTemplate.find(new Query(Criteria.where("createDate").gt(DateUtil.getTheDayByDate(new Date(),-11))).limit(1), User.class);
        System.out.println(JsonUtil.toJson(users));
    }

    @Test
    public void findList1() {
        List<User> users = mongoTemplate.find(new Query(Criteria.where("email").is("54434@qq.com")).limit(1), User.class);
        System.out.println(JsonUtil.toJson(users));
    }

    @Test
    public void findTempImage() {
        Document tempImages = mongoTemplate.getCollection("tempImage").find().limit(3).first();
        System.out.println(JsonUtil.toJson(tempImages));
    }

    @Test
    public void findGtDemo() {
        Date date = DateUtil.getDateFormat(DateUtil.FORMAT_YYYY_MM_DD_HHMMSS,"2022-07-19 10:48:16");
        List<MongoDemo> list = mongoTemplate.find(new Query(Criteria.where("createDate").gt(date)).limit(1), MongoDemo.class);
        System.out.println(JsonUtil.toJson(list));
    }
}

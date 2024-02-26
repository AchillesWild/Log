package com.test.mongodb;

import com.achilles.server.entity.User;
import com.achilles.tool.date.DateUtil;
import com.test.BaseTest;
import com.test.mongodb.model.MongoDemo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MongodbAddTest extends BaseTest {


    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void add() {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setMobile("18701514747");
        user.setEmail("54434@qq.com");
        user.setCreateDate(DateUtil.getTheDayByDate(new Date(),-10));
        user.setCreateTime(System.currentTimeMillis());
        user = mongoTemplate.insert(user);
        System.out.println("SUCCESS ！ user : " + user);
    }

//    @Test
//    public void addImage() throws FileNotFoundException {
//        String path = "D:\\Java\\Work\\AchillesWild\\src\\main\\resources\\file\\44.jpg";
//        FileInputStream inputStream = new FileInputStream(path);
//        TempImage tempImage = new TempImage();
//        tempImage.setId(System.currentTimeMillis());
//        tempImage.setUuid(GenerateUniqueUtil.getUuId());
//        tempImage.setBizUuid(GenerateUniqueUtil.getUuId());
//        byte[] bytes = FileUtil.toByteArray(inputStream);
//        tempImage.setImage(bytes);
//        tempImage.setUrl("http");
//        tempImage.setStatus(1);
//        tempImage.setCreateDate(new Date());
//        tempImage.setUpdateDate(new Date());
//        tempImage = mongoTemplate.insert(tempImage);
//        System.out.println("SUCCESS ！ tempImage : " + tempImage);
//    }

    @Test
    public void addDemo() {
        MongoDemo mongoDemo = new MongoDemo();
        mongoDemo.setName("achilles3");
        mongoDemo.setCreateDate(DateUtil.getTheDayByDate(new Date(),-10));
        mongoDemo.setCreateTime(System.currentTimeMillis());
        List<MongoDemo> list = new ArrayList<>();
        list.add(mongoDemo);
        list = (List<MongoDemo>) mongoTemplate.insert(list,"mongo_demo");
        System.out.println("SUCCESS ！ user : " + list);
    }
}

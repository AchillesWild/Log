package com.test.mongodb;

import com.achilles.server.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongodbDeleteTest extends BaseTest {


    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void delete() {

        User user = new User();
        user.setId(2L);
        DeleteResult result = mongoTemplate.remove(user);
        System.out.println();
    }


}

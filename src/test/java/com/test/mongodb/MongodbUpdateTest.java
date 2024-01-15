package com.test.mongodb;

import com.achilles.server.entity.User;
import com.mongodb.client.result.UpdateResult;
import com.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class MongodbUpdateTest extends BaseTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void updateFirst() {

        UpdateResult result = mongoTemplate.updateFirst(new Query(Criteria.where("id").is(1l)), Update.update("email","22@qq.com"), User.class);
        System.out.println();
    }

    @Test
    public void updateMulti() {

        UpdateResult result = mongoTemplate.updateMulti(new Query(Criteria.where("email").is("54434@qq.com")), Update.update("email","44@qq.com"), User.class);
        System.out.println();
    }
}

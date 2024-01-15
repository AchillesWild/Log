package com.test;

import com.achilles.SpringBootMongoDBApplication;
import com.achilles.server.controller.SpringBootMongoDBController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= SpringBootMongoDBApplication.class)
public class BaseTest {

}

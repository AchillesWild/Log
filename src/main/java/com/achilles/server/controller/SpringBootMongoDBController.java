package com.achilles.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/mongoDB")
@Slf4j
public class SpringBootMongoDBController {


    @GetMapping(path = "/test")
    public String getConfig(){
        log.info("-----------------***************Achilles********");
        return "------------------***************Achilles******************__________________________________";
    }



}

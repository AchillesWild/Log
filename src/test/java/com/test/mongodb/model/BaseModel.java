package com.test.mongodb.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseModel {

    private String id;

    private Date createDate;

    private Date updateDate;

    private Long createTime;

    private Long updateTime;
}

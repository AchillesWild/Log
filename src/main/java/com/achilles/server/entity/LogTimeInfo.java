package com.achilles.server.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LogTimeInfo {

    private String id;

    private String clz;

    private String method;

    private String params;

    private Integer time;

    private String traceId;

    private Date createDate;

    private Long createTime;
}

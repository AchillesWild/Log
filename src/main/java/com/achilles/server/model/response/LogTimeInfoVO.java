package com.achilles.server.model.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTimeInfoVO {

    private String id;

    private String clz;

    private String method;

    private String params;

    private Integer time;

    private String traceId;

    private Long createTime;

    private String createTimeStr;
}

package com.achilles.server.model.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTimeInfoCommonVO {

    private String id;

    private String clz;

    private String method;

    private Integer time;

    private String traceId;

    private String createTimeStr;
}

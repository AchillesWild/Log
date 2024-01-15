package com.achilles.server.model.query;


import com.achilles.model.query.PageQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTimeInfoQuery extends PageQuery  {

    private String clz;

    private String method;

    private Integer timeMIn;

    private Integer timeMax;

    private String traceId;

    private Long createTimeStart;

    private Long createTimeEnd;
}

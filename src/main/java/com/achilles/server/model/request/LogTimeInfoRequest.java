package com.achilles.server.model.request;


import com.achilles.model.request.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTimeInfoRequest extends PageRequest {

    private String clz;

    private String method;

    private Integer timeMIn;

    private Integer timeMax;

    private String traceId;

    private String createTimeStart;

    private String createTimeEnd;
}

package com.achilles.server.model.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogTimeInfoCountVO {

    private long countMySql;

    private long countMongo;

}


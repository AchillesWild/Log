package com.achilles.server.dao;


import com.achilles.server.entity.LogTimeInfo;
import com.achilles.server.model.query.LogTimeInfoQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogTimeInfoDao {

    int batchInsert(List<LogTimeInfo> list);

    List<LogTimeInfo> query(@Param("clz")String clz,
                            @Param("method") String method,
                            @Param("timeMIn")Integer timeMIn,
                            @Param("timeMax")Integer timeMax,
                            @Param("offSet") int offSet,
                            @Param("pageSize") int pageSize);

    List<LogTimeInfo> query(LogTimeInfoQuery query);

    long count(@Param("clz")String clz,
                            @Param("method") String method,
                            @Param("timeMIn")Integer timeMIn,
                            @Param("timeMax")Integer timeMax);

    long count(LogTimeInfoQuery query);

    int deleteAll();

    int deleteById(@Param("id") String id);

    LogTimeInfo selectById(@Param("id") String id);

    List<LogTimeInfo> selectByTraceId(@Param("traceId") String traceId);
}

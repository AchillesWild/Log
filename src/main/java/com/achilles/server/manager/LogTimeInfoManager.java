package com.achilles.server.manager;

import com.achilles.server.entity.LogTimeInfo;
import com.achilles.server.model.query.LogTimeInfoQuery;

import java.util.List;

public interface LogTimeInfoManager {

    List<LogTimeInfo> addLogs(List<LogTimeInfo> logTimeInfoList);

    long getMongoCount(LogTimeInfoQuery logTimeInfoQuery);

    long getMysqlCount(LogTimeInfoQuery logTimeInfoQuery);

    LogTimeInfo getById(String id);

    List<LogTimeInfo> getByTraceId(String traceId);

    void deleteById(String id);

    List<LogTimeInfo> getSlowLogs(LogTimeInfoQuery logTimeInfoQuery);

    long getCount(LogTimeInfoQuery logTimeInfoQuery);
}

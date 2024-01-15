package com.achilles.server.service;

import com.achilles.server.model.query.LogTimeInfoQuery;
import com.achilles.server.model.response.LogTimeInfoCommonVO;

import java.util.List;

public interface LogTimeInfoService {

    List<LogTimeInfoCommonVO> getSlowLogs(LogTimeInfoQuery logTimeInfoQuery);

    long getTotal(LogTimeInfoQuery logTimeInfoQuery);
}

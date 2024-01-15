package com.achilles.server.biz;


import com.achilles.model.response.DataResult;
import com.achilles.server.model.query.LogTimeInfoQuery;
import com.achilles.server.model.response.LogTimeInfoCommonVO;
import com.achilles.server.model.response.LogTimeInfoCountVO;
import com.achilles.server.model.response.LogTimeInfoVO;

import java.util.List;

public interface LogTimeInfoBiz {

    DataResult<List<LogTimeInfoCommonVO>> getSlowLogs(LogTimeInfoQuery logTimeInfoQuery);

    LogTimeInfoCountVO getCount(LogTimeInfoQuery logTimeInfoQuery);

    LogTimeInfoVO getById(String id);

    void deleteById(String id);

    List<LogTimeInfoCommonVO> getByTraceId(String traceId);
}

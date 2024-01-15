package com.achilles.server.controller;

import com.achilles.model.response.DataResult;
import com.achilles.model.response.PageResult;
import com.achilles.model.response.code.BaseResultCode;
import com.achilles.server.common.exception.BizException;
import com.achilles.server.model.query.LogTimeInfoQuery;
import com.achilles.server.model.request.LogTimeInfoRequest;
import com.achilles.server.model.response.LogTimeInfoCommonVO;
import com.achilles.server.model.response.LogTimeInfoCountVO;
import com.achilles.server.model.response.LogTimeInfoVO;
import com.achilles.tool.date.DateUtil;
import com.achilles.server.biz.LogTimeInfoBiz;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// http://localhost:8080/log/get/count

@Slf4j
@RestController
@RequestMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogQryController {

    @Autowired
    LogTimeInfoBiz logTimeInfoBiz;

    @GetMapping("/get/slow")
    public DataResult<List<LogTimeInfoCommonVO>> getSlowLogs(LogTimeInfoRequest request){

        LogTimeInfoQuery logTimeInfoQuery = getLogTimeInfoQuery(request);
        DataResult<List<LogTimeInfoCommonVO>> pageResult = logTimeInfoBiz.getSlowLogs(logTimeInfoQuery);

        return pageResult;
    }

    @GetMapping("/get/count")
    public DataResult<LogTimeInfoCountVO> getCount(LogTimeInfoRequest request){

        LogTimeInfoQuery logTimeInfoQuery = getLogTimeInfoQuery(request);
        LogTimeInfoCountVO logTimeInfoCountVO = logTimeInfoBiz.getCount(logTimeInfoQuery);
        if (logTimeInfoCountVO == null) {
            throw new BizException(BaseResultCode.EXCEPTION);
        }

        return DataResult.success(logTimeInfoCountVO);
    }

    @GetMapping("/get/{id}")
    public DataResult<LogTimeInfoVO> getById(@PathVariable("id") String id){

        LogTimeInfoVO logTimeInfoVO = logTimeInfoBiz.getById(id);
        if (logTimeInfoVO == null) {
            throw new BizException(BaseResultCode.DATA_NOT_EXISTS);
        }

        return DataResult.success(logTimeInfoVO);
    }

    @GetMapping("/get/trace/{traceId}")
    public PageResult<LogTimeInfoCommonVO> getByTraceId(@PathVariable("traceId") String traceId){

        List<LogTimeInfoCommonVO> logTimeInfoCommonVOList = logTimeInfoBiz.getByTraceId(traceId);
        if (logTimeInfoCommonVOList.size() == 0) {
            return PageResult.noData();
        }
        PageResult<LogTimeInfoCommonVO>  pageResult = PageResult.success(logTimeInfoCommonVOList);

        return pageResult;
    }

    private LogTimeInfoQuery getLogTimeInfoQuery(LogTimeInfoRequest request) {
        LogTimeInfoQuery logTimeInfoQuery = new LogTimeInfoQuery();
        BeanUtils.copyProperties(request,logTimeInfoQuery);
        if (StringUtils.isNotEmpty(request.getCreateTimeStart())) {
            logTimeInfoQuery.setCreateTimeStart(DateUtil.getTimeMillis(DateUtil.FORMAT_YYYY_MM_DD_HHMMSS,request.getCreateTimeStart()));
        }
        if (StringUtils.isNotEmpty(request.getCreateTimeEnd())) {
            logTimeInfoQuery.setCreateTimeEnd(DateUtil.getTimeMillis(DateUtil.FORMAT_YYYY_MM_DD_HHMMSS,request.getCreateTimeEnd()));
        }
        return logTimeInfoQuery;
    }
}

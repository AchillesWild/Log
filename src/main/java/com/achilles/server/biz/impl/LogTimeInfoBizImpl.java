package com.achilles.server.biz.impl;

import com.achilles.model.response.DataResult;
import com.achilles.model.response.PageResult;
import com.achilles.server.entity.LogTimeInfo;
import com.achilles.server.manager.LogTimeInfoManager;
import com.achilles.server.model.query.LogTimeInfoQuery;
import com.achilles.server.model.response.LogTimeInfoCommonVO;
import com.achilles.server.model.response.LogTimeInfoCountVO;
import com.achilles.server.model.response.LogTimeInfoVO;
import com.achilles.server.service.LogTimeInfoService;
import com.achilles.tool.date.DateUtil;
import com.achilles.server.biz.LogTimeInfoBiz;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogTimeInfoBizImpl implements LogTimeInfoBiz {

    @Autowired
    LogTimeInfoService logTimeInfoService;

    @Autowired
    LogTimeInfoManager logTimeInfoManager;

    @Override
    public DataResult<List<LogTimeInfoCommonVO>> getSlowLogs(LogTimeInfoQuery logTimeInfoQuery) {

        List<LogTimeInfoCommonVO> logTimeInfoVOList = logTimeInfoService.getSlowLogs(logTimeInfoQuery);
        if (logTimeInfoVOList.size() == 0) {
            return PageResult.noPageData();
        }

        long total = logTimeInfoService.getTotal(logTimeInfoQuery);

        PageResult<LogTimeInfoCommonVO> pageResult = PageResult.success(logTimeInfoVOList,total);

        return pageResult;
    }

    @Override
    public LogTimeInfoCountVO getCount(LogTimeInfoQuery logTimeInfoQuery) {
        LogTimeInfoCountVO logTimeInfoCountVO = new LogTimeInfoCountVO();

        long countMongo = logTimeInfoManager.getMongoCount(logTimeInfoQuery);
        logTimeInfoCountVO.setCountMongo(countMongo);

        long countMySql = logTimeInfoManager.getMysqlCount(logTimeInfoQuery);
        logTimeInfoCountVO.setCountMySql(countMySql);

        return logTimeInfoCountVO;
    }

    @Override
    public LogTimeInfoVO getById(String id) {
        LogTimeInfo logTimeInfo = logTimeInfoManager.getById(id);
        if (logTimeInfo == null) {
            return null;
        }

        LogTimeInfoVO logTimeInfoVO = new LogTimeInfoVO();
        BeanUtils.copyProperties(logTimeInfo,logTimeInfoVO);
        logTimeInfoVO.setCreateTimeStr(DateUtil.getStrDateFormat(DateUtil.FORMAT_YYYY_MM_DD_HHMMSS,new Date(logTimeInfo.getCreateTime())));
        return logTimeInfoVO;
    }

    @Override
    public void deleteById(String id) {
        logTimeInfoManager.deleteById(id);
    }

    @Override
    public List<LogTimeInfoCommonVO> getByTraceId(String traceId) {

        List<LogTimeInfo> logTimeInfoList = logTimeInfoManager.getByTraceId(traceId);
        if (logTimeInfoList.size() == 0) {
            return new ArrayList<>();
        }

        List<LogTimeInfoCommonVO> logTimeInfoVOList = logTimeInfoList.stream().map(logTimeInfo -> {
            LogTimeInfoCommonVO logTimeInfoVO = new LogTimeInfoCommonVO();
            BeanUtils.copyProperties(logTimeInfo,logTimeInfoVO);
            logTimeInfoVO.setCreateTimeStr(DateUtil.getStrDateFormat(DateUtil.FORMAT_YYYY_MM_DD_HHMMSS,new Date(logTimeInfo.getCreateTime())));
            return logTimeInfoVO;
        }).collect(Collectors.toList());

        logTimeInfoVOList.sort(Comparator.comparing(LogTimeInfoCommonVO::getTime).reversed());

        return logTimeInfoVOList;
    }
}

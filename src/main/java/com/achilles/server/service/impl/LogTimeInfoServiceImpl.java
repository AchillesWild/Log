package com.achilles.server.service.impl;

import com.achilles.server.entity.LogTimeInfo;
import com.achilles.server.manager.LogTimeInfoManager;
import com.achilles.server.model.query.LogTimeInfoQuery;
import com.achilles.server.model.response.LogTimeInfoCommonVO;
import com.achilles.server.service.LogTimeInfoService;
import com.achilles.tool.date.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogTimeInfoServiceImpl implements LogTimeInfoService {


    @Autowired
    LogTimeInfoManager logTimeInfoManager;

    @Override
    public List<LogTimeInfoCommonVO> getSlowLogs(LogTimeInfoQuery logTimeInfoQuery) {

        List<LogTimeInfo> logTimeInfoList = logTimeInfoManager.getSlowLogs(logTimeInfoQuery);
        if (logTimeInfoList.size() == 0) {
            return new ArrayList<>();
        }

        List<LogTimeInfoCommonVO> logTimeInfoVOList = logTimeInfoList.stream().map(logTimeInfo -> {
            LogTimeInfoCommonVO logTimeInfoVO = new LogTimeInfoCommonVO();
            BeanUtils.copyProperties(logTimeInfo,logTimeInfoVO);
            logTimeInfoVO.setCreateTimeStr(DateUtil.getStrDateFormat(DateUtil.FORMAT_YYYY_MM_DD_HHMMSS,new Date(logTimeInfo.getCreateTime())));
            return logTimeInfoVO;
        }).collect(Collectors.toList());

        return logTimeInfoVOList;
    }

    @Override
    public long getTotal(LogTimeInfoQuery logTimeInfoQuery) {
        return logTimeInfoManager.getCount(logTimeInfoQuery);
    }
}

package com.achilles.server.manager.impl;

import com.achilles.server.dao.LogTimeInfoDao;
import com.achilles.server.dao.LogTimeInfoMongo;
import com.achilles.server.entity.LogTimeInfo;
import com.achilles.server.manager.LogTimeInfoManager;
import com.achilles.server.model.query.LogTimeInfoQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LogTimeInfoManagerImpl implements LogTimeInfoManager {

    @Autowired
    LogTimeInfoDao logTimeInfoDao;

    @Autowired
    LogTimeInfoMongo logTimeInfoMongo;

    public List<LogTimeInfo> getSlowLogs(LogTimeInfoQuery logTimeInfoQuery) {

        Query query = getQuery(logTimeInfoQuery);
        query.with(Sort.by(
                Sort.Order.desc("time")
        ));

        List<LogTimeInfo> logTimeInfoList = logTimeInfoMongo.query(query,logTimeInfoQuery.getOffSet(),logTimeInfoQuery.getPageSize());
        if (logTimeInfoList.size() == 0) {
            logTimeInfoList = logTimeInfoDao.query(logTimeInfoQuery);
        }

        return logTimeInfoList;
    }

    @Override
    public long getCount(LogTimeInfoQuery logTimeInfoQuery) {

        Query query = getQuery(logTimeInfoQuery);
        long count = logTimeInfoMongo.count(query);
        if (count == 0) {
            count = logTimeInfoDao.count(logTimeInfoQuery);
        }

        return count;
    }

    private Query getQuery(LogTimeInfoQuery logTimeInfoQuery) {

        Query query = new Query();

        if (StringUtils.isNotEmpty(logTimeInfoQuery.getClz())) {
            Pattern clzPattern = Pattern.compile("^.*" + logTimeInfoQuery.getClz() + ".*$", Pattern.CASE_INSENSITIVE);
            Criteria criteria = Criteria.where("clz").regex(clzPattern);
            query.addCriteria(criteria);
        }
        if (StringUtils.isNotEmpty(logTimeInfoQuery.getMethod())) {
            Pattern methodPattern = Pattern.compile("^.*" + logTimeInfoQuery.getMethod() + ".*$", Pattern.CASE_INSENSITIVE);
            Criteria criteria = Criteria.where("method").regex(methodPattern);
            query.addCriteria(criteria);
        }
        if (logTimeInfoQuery.getTimeMIn() != null) {
            Criteria criteria = Criteria.where("time").gte(logTimeInfoQuery.getTimeMIn());
            if (logTimeInfoQuery.getTimeMax() != null) {
                criteria = criteria.lte(logTimeInfoQuery.getTimeMax());
            }
            query.addCriteria(criteria);
        } else {
            if (logTimeInfoQuery.getTimeMax() != null) {
                Criteria criteria = Criteria.where("time").lte(logTimeInfoQuery.getTimeMax());
                query.addCriteria(criteria);
            }
        }
        if (StringUtils.isNotEmpty(logTimeInfoQuery.getTraceId())) {
            Criteria criteria = Criteria.where("traceId").is(logTimeInfoQuery.getTraceId());
            query.addCriteria(criteria);
        }
        if (logTimeInfoQuery.getCreateTimeStart() != null) {
            Criteria criteria = Criteria.where("createTime").gte(logTimeInfoQuery.getCreateTimeStart());
            if (logTimeInfoQuery.getCreateTimeEnd() != null) {
                criteria = criteria.lte(logTimeInfoQuery.getCreateTimeEnd());
            }
            query.addCriteria(criteria);
        } else {
            if (logTimeInfoQuery.getCreateTimeEnd() != null) {
                Criteria criteria = Criteria.where("createTime").lte(logTimeInfoQuery.getCreateTimeEnd());
                query.addCriteria(criteria);
            }
        }
        return query;
    }

    @Override
    public long getMongoCount(LogTimeInfoQuery logTimeInfoQuery) {
        Query query = getQuery(logTimeInfoQuery);
        long countMongo = logTimeInfoMongo.count(query);
        return countMongo;
    }

    @Override
    public long getMysqlCount(LogTimeInfoQuery logTimeInfoQuery) {
        long countMySql = logTimeInfoDao.count(logTimeInfoQuery);
        return countMySql;
    }

    @Override
    public LogTimeInfo getById(String id) {
        LogTimeInfo logTimeInfo = logTimeInfoMongo.selectById(id);
        if (logTimeInfo == null) {
            logTimeInfo = logTimeInfoDao.selectById(id);
        }
        return logTimeInfo;
    }

    @Override
    public List<LogTimeInfo> getByTraceId(String traceId) {
        Criteria criteria = Criteria.where("traceId").is(traceId);
        List<LogTimeInfo> logTimeInfoList = logTimeInfoMongo.query(criteria,100);
        if (logTimeInfoList.size() == 0) {
            logTimeInfoList = logTimeInfoDao.selectByTraceId(traceId);
        }

        return logTimeInfoList;
    }

    @Override
    public void deleteById(String id) {
        logTimeInfoMongo.deleteById(id);
        logTimeInfoDao.deleteById(id);

    }

    @Override
    public List<LogTimeInfo> addLogs(List<LogTimeInfo> logTimeInfoList) {

        if (CollectionUtils.isEmpty(logTimeInfoList)){
            throw new IllegalArgumentException("list can not be empty !");
        }

        List<LogTimeInfo> newLogTimeInfoList = logTimeInfoList.stream().map(logTimeInfo -> {
            LogTimeInfo newLogTimeInfo = new LogTimeInfo();
            BeanUtils.copyProperties(logTimeInfo, newLogTimeInfo);
            newLogTimeInfo.setCreateDate(new Date());
            newLogTimeInfo.setCreateTime(System.currentTimeMillis());
            return newLogTimeInfo;
        }).collect(Collectors.toList());;

        logTimeInfoMongo.add(newLogTimeInfoList);
        logTimeInfoDao.batchInsert(newLogTimeInfoList);
        return logTimeInfoList;
    }
}

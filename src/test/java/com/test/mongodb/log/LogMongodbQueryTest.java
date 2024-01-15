package com.test.mongodb.log;

import com.achilles.server.dao.LogTimeInfoMongo;
import com.achilles.server.entity.LogTimeInfo;
import com.achilles.tool.date.DateUtil;
import com.achilles.tool.json.JsonUtil;
import com.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class LogMongodbQueryTest extends BaseTest {


    @Autowired
    LogTimeInfoMongo logTimeInfoMongo;

    @Test
    public void findLogsLike() {
        Query query = new Query();
        String pattern_name = "Con";
        Pattern pattern = Pattern.compile("^.*" + pattern_name + ".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("clz").regex(pattern));
        List<LogTimeInfo> list = logTimeInfoMongo.query(query,0,2);
        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void findLogsRange() {
        Date date = DateUtil.getDateFormat(DateUtil.FORMAT_YYYY_MM_DD_HHMMSS,"2022-07-19 10:48:16");
        Criteria criteria = Criteria.where("createDate").gt(date);
        List<LogTimeInfo> list = logTimeInfoMongo.query(criteria,2);
        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void findSort() {
        Query query = new Query();
        String pattern_name = "Con";
        Pattern pattern = Pattern.compile("^.*" + pattern_name + ".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("clz").regex(pattern));
        query.with(Sort.by(
                Sort.Order.desc("time")
        ));
        List<LogTimeInfo> list = logTimeInfoMongo.query(query,0,2);
        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void getCount() {
        Query query = new Query();
        String pattern_name = "Con";
        Pattern pattern = Pattern.compile("^.*" + pattern_name + ".*$", Pattern.CASE_INSENSITIVE);
        query.addCriteria(Criteria.where("clz").regex(pattern));
        long count = logTimeInfoMongo.count(query);
        System.out.println(count);
    }
}

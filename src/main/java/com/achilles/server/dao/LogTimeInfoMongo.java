package com.achilles.server.dao;

import com.achilles.server.entity.LogTimeInfo;
import com.achilles.tool.generate.unique.GenerateUniqueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LogTimeInfoMongo {

    private static final String COLLECTION_NAME = "log_time_info";

    @Value("${if.mongodb.open}")
    Boolean ifMongodbOpen;

    @Autowired
    MongoTemplate mongoTemplate;

    public void add(List<LogTimeInfo> list){

        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("list can not be null !");
        }

        if(!ifMongodbOpen){
            list.stream().forEach(logTimeInfo -> {
                String uuid = GenerateUniqueUtil.getUuId();
                logTimeInfo.setId(uuid);
            });
            return;
        }

        mongoTemplate.insert(list,COLLECTION_NAME);
    }

    public List<LogTimeInfo> query(Criteria criteria,int limit){
        if(!ifMongodbOpen){
            return new ArrayList<>();
        }

        if (limit == 0) {
            limit = 30000;
        }
        List<LogTimeInfo> logTimeInfoList = mongoTemplate.find((new Query(criteria)).limit(limit), LogTimeInfo.class,COLLECTION_NAME);
        return logTimeInfoList;
    }

    public List<LogTimeInfo> query(Query query,int skip ,int limit){
        if(!ifMongodbOpen){
            return new ArrayList<>();
        }

        if (query == null) {
            query = new Query();
        }
        if (skip < 0) {
            skip = 0;
        }
        if (limit <= 0) {
            limit = 1;
        }
        if (limit > 20000) {
            limit = 20000;
        }
        query.skip(skip);
        List<LogTimeInfo> logTimeInfoList = mongoTemplate.find((query).limit(limit), LogTimeInfo.class,COLLECTION_NAME);
        return logTimeInfoList;
    }

    public long count(Query query){
        if(!ifMongodbOpen){
            return 0;
        }

        if (query == null) {
            query = new Query();
        }
        long count = mongoTemplate.count(query, LogTimeInfo.class, COLLECTION_NAME);
        return count;
    }

    public void deleteAll(){
        if(!ifMongodbOpen){
            return;
        }
        mongoTemplate.remove(new Query(),COLLECTION_NAME);
    }

    public void deleteById(String id){
        if(!ifMongodbOpen){
            return;
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, LogTimeInfo.class, COLLECTION_NAME);
    }

    public LogTimeInfo selectById(String id){
        if(!ifMongodbOpen){
            return null;
        }

        LogTimeInfo logTimeInfo = mongoTemplate.findById(id, LogTimeInfo.class,COLLECTION_NAME);
        return logTimeInfo;
    }
}

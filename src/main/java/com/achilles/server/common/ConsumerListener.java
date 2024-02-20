package com.achilles.server.common;

import com.achilles.server.entity.LogTimeInfo;
import com.achilles.server.manager.LogTimeInfoManager;
import com.achilles.tool.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ConsumerListener {

    @Autowired
    LogTimeInfoManager logTimeInfoManager;
    private final List<LogTimeInfo> logTimeInfoList = new ArrayList<>();

    @KafkaListener(topics = "TimeLog")
    public void consumeMessage(ConsumerRecord<?,?> record){
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()){
            Object message = kafkaMessage.get();
            log.info(" record : {}", record);
            log.info("message : {}", message);
            LogTimeInfo logTimeInfo = JsonUtil.fromJson(String.valueOf(message), LogTimeInfo.class);
            logTimeInfoList.add(logTimeInfo);
            if (logTimeInfoList.size() == 10) {
                log.info("addLogs ***********************************************");
                logTimeInfoManager.addLogs(logTimeInfoList);
                logTimeInfoList.clear();
            }
        }
    }
}
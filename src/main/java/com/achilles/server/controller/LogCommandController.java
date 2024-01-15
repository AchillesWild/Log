package com.achilles.server.controller;

import com.achilles.model.response.BaseResult;
import com.achilles.model.response.code.BaseResultCode;
import com.achilles.server.common.exception.BizException;
import com.achilles.server.dao.LogTimeInfoDao;
import com.achilles.server.dao.LogTimeInfoMongo;
import com.achilles.server.model.response.LogTimeInfoVO;
import com.achilles.server.biz.LogTimeInfoBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/log", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogCommandController {

    @Autowired
    LogTimeInfoDao logTimeInfoDao;

    @Autowired
    LogTimeInfoMongo logTimeInfoMongo;

    @Autowired
    LogTimeInfoBiz logTimeInfoBiz;

    @DeleteMapping("/delete/type/{type}")
    public BaseResult deleteLogs(@PathVariable("type") String type){

        if ("0".equals(type)) {
            logTimeInfoDao.deleteAll();
            logTimeInfoMongo.deleteAll();
        }
        if ("1".equals(type)) {
            logTimeInfoDao.deleteAll();
        }
        if ("2".equals(type)) {
            logTimeInfoMongo.deleteAll();
        }

        return new BaseResult();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResult deleteById(@PathVariable("id") String id){

        LogTimeInfoVO logTimeInfoVO = logTimeInfoBiz.getById(id);
        if (logTimeInfoVO == null) {
            throw new BizException(BaseResultCode.DATA_NOT_EXISTS);
        }

        logTimeInfoBiz.deleteById(id);

        return new BaseResult();
    }
}

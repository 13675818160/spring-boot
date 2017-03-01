package com.spring.service;

import com.spring.base.BizException;
import com.spring.service.cache.DictSysParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Created by maobg on 2015/7/13.
 */
@Scope("singleton")
@Component
public class ScheduledTasks {


    @Autowired
    DictSysParameter dictSysParameter;

    @Scheduled(fixedDelay = 1800000)   //30分钟执行一次
    public void refreshSystemParameters() {
        dictSysParameter.refresh();
    }

    @Scheduled(cron = "0 0 0 * * ?")   //每天凌晨0点执行一次
    public void convertRecordStatusToSettled() throws BizException,ParseException{

    }

}

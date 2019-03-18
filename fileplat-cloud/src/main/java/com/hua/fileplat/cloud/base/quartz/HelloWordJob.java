package com.hua.fileplat.cloud.base.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  HelloWordJob implements Job {
    private static Logger logger= LoggerFactory.getLogger(HelloWordJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("hello word");
    }
}

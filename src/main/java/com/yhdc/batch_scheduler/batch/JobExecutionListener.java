package com.yhdc.batch_scheduler.batch;

public interface JobExecutionListener {

    void beforeJob(JobExecution jobExecution);
    void afterJob(JobExecution jobExecution);

}

package com.yhdc.batch_scheduler.batch;

public interface JobExecutionListener {

    void beforeJob(BatchJobExecution batchJobExecution);
    void afterJob(BatchJobExecution batchJobExecution);

}

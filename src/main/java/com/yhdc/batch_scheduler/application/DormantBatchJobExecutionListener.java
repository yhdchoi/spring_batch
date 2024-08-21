package com.yhdc.batch_scheduler.application;

import com.yhdc.batch_scheduler.batch.BatchJobExecution;
import com.yhdc.batch_scheduler.batch.JobExecutionListener;
import com.yhdc.batch_scheduler.provider.EmailProvider;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchJobExecutionListener implements JobExecutionListener {

    private final EmailProvider emailProvider;

    public DormantBatchJobExecutionListener() {
        this.emailProvider = new EmailProvider.Fake();
    }


    @Override
    public void beforeJob(BatchJobExecution batchJobExecution) {

    }

    @Override
    public void afterJob(BatchJobExecution batchJobExecution) {
        emailProvider.sendEmail("admin@test.com", "Batch completed", "Batch has been executed. Status: " + batchJobExecution.getStatus());

    }
}

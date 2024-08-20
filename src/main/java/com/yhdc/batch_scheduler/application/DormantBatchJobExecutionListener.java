package com.yhdc.batch_scheduler.application;

import com.yhdc.batch_scheduler.batch.JobExecution;
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
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        emailProvider.sendEmail("admin@test.com", "Batch completed", "Batch has been executed. Status: " + jobExecution.getStatus());

    }
}

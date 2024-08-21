package com.yhdc.batch_scheduler.application;

import com.yhdc.batch_scheduler.batch.DormantBatchJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public DormantBatchJob dormantBatchJob(DormantBatchTasklet dormantBatchTasklet, DormantBatchJobExecutionListener dormantBatchJobExecutionListener) {
        return new DormantBatchJob(dormantBatchTasklet, dormantBatchJobExecutionListener);
    }

}

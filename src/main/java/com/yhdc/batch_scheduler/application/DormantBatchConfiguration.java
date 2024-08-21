package com.yhdc.batch_scheduler.application;

import com.yhdc.batch_scheduler.batch.BatchJob;
import com.yhdc.batch_scheduler.entity.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public BatchJob dormantBatchJob(DormantBatchItemReader dormantBatchItemReader,
                                    DormantBatchItemProcessor dormantBatchItemProcessor,
                                    DormantBatchItemWriter dormantBatchItemWriter,
                                    DormantBatchJobExecutionListener dormantBatchJobExecutionListener) {

        return BatchJob.builder()
                .itemReader(dormantBatchItemReader)
                .itemProcessor(dormantBatchItemProcessor)
                .itemWriter(dormantBatchItemWriter)
                .jobListener(dormantBatchJobExecutionListener)
                .build();

    }

}

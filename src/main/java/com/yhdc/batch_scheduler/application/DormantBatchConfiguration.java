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

        final DormantBatchTasklet<Customer, Customer> tasklet = new DormantBatchTasklet<>(dormantBatchItemReader, dormantBatchItemProcessor, dormantBatchItemWriter);

        return new BatchJob(tasklet, dormantBatchJobExecutionListener);
    }

}

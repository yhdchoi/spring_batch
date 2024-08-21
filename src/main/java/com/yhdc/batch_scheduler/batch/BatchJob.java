package com.yhdc.batch_scheduler.batch;

import com.yhdc.batch_scheduler.application.DormantBatchTasklet;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
public class BatchJob {

    private final BatchTasklet batchTasklet;
    private final JobExecutionListener jobListener;

    public BatchJob(BatchTasklet batchTasklet) {
        this(batchTasklet, null);
    }

    public BatchJob(BatchTasklet batchTasklet, JobExecutionListener jobListener) {
        this.batchTasklet = batchTasklet;
        this.jobListener = Objects.requireNonNullElseGet(jobListener, () -> new JobExecutionListener() {
            @Override
            public void beforeJob(BatchJobExecution batchJobExecution) {

            }

            @Override
            public void afterJob(BatchJobExecution batchJobExecution) {

            }
        });
    }


    @Builder
    public BatchJob(ItemReader itemReader, ItemProcessor itemProcessor, ItemWriter itemWriter, JobExecutionListener jobListener) {
        this(new DormantBatchTasklet<>(itemReader, itemProcessor, itemWriter));
    }


    public BatchJobExecution execute() {

        final BatchJobExecution batchJobExecution = new BatchJobExecution();
        batchJobExecution.setStatus(BatchStatus.STARTING);
        batchJobExecution.setStartTime(LocalDateTime.now());

        jobListener.beforeJob(batchJobExecution);

        try {
            batchTasklet.execute();
            batchJobExecution.setStatus(BatchStatus.COMPLETED);

        } catch (Exception e) {
            batchJobExecution.setStatus(BatchStatus.FAILED);
//            log.error(e.getMessage(), e);
        }

        batchJobExecution.setEndTime(LocalDateTime.now());
        jobListener.afterJob(batchJobExecution);

        return batchJobExecution;
    }


}

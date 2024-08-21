package com.yhdc.batch_scheduler.batch;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
public class DormantBatchJob {

    private final Tasklet tasklet;
    private final JobExecutionListener jobListener;

    public DormantBatchJob(Tasklet tasklet) {
        this(tasklet, null);
    }

    public DormantBatchJob(Tasklet tasklet, JobExecutionListener jobListener) {
        this.tasklet = tasklet;
        this.jobListener = Objects.requireNonNullElseGet(jobListener, () -> new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {

            }

            @Override
            public void afterJob(JobExecution jobExecution) {

            }
        });
    }


    public JobExecution execute() {

        final JobExecution jobExecution = new JobExecution();
        jobExecution.setStatus(BatchStatus.STARTING);
        jobExecution.setStartTime(LocalDateTime.now());

        jobListener.beforeJob(jobExecution);

        try {
            tasklet.execute();
            jobExecution.setStatus(BatchStatus.COMPLETED);

        } catch (Exception e) {
            jobExecution.setStatus(BatchStatus.FAILED);
//            log.error(e.getMessage(), e);
        }

        jobExecution.setEndTime(LocalDateTime.now());
        jobListener.afterJob(jobExecution);

        return jobExecution;
    }


}

package com.yhdc.batch_scheduler.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class BatchJob {

    private final Tasklet tasklet;
    private final JobExecutionListener jobListener;

    public BatchJob(Tasklet tasklet, JobExecutionListener jobListener) {
        this.tasklet = tasklet;
        this.jobListener = jobListener;
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

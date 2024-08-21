package com.yhdc.batch_scheduler.batch;

public interface BatchTasklet {
    void execute() throws Exception;
}

package com.yhdc.batch_scheduler.batch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BatchJobExecution {

    private BatchStatus status;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

}

package com.yhdc.batch_scheduler.batch;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BatchStatus {

    STARTING,
    FAILED,
    COMPLETED
}

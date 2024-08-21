package com.yhdc.batch_scheduler.batch;

import org.springframework.stereotype.Component;

@Component
public interface ItemWriter<O> {

    void write(O item);
}

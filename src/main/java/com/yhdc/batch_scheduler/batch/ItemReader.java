package com.yhdc.batch_scheduler.batch;

import org.springframework.stereotype.Component;

@Component
public interface ItemReader<I> {
    I read();
}

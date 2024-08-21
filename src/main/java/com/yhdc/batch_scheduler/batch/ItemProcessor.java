package com.yhdc.batch_scheduler.batch;

import org.springframework.stereotype.Component;

@Component
public interface ItemProcessor<I, O> {
    O process(I item);
}

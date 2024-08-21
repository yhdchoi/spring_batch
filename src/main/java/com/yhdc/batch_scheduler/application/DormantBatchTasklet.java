package com.yhdc.batch_scheduler.application;

import com.yhdc.batch_scheduler.batch.BatchTasklet;
import com.yhdc.batch_scheduler.batch.ItemProcessor;
import com.yhdc.batch_scheduler.batch.ItemReader;
import com.yhdc.batch_scheduler.batch.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchTasklet<I, O> implements BatchTasklet {

    private final ItemReader<I> itemReader;
    private final ItemProcessor<I, O> itemProcessor;
    private final ItemWriter<O> itemWriter;

    public DormantBatchTasklet(ItemReader<I> itemReader,
                               ItemProcessor<I, O> itemProcessor,
                               ItemWriter<O> itemWriter) {
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
    }

    @Override
    public void execute() throws Exception {
        while (true) {
            // 1. Read
            final I itemRead = itemReader.read();
            if (itemRead == null) break;

            // 2. Process
            final O processedItem = itemProcessor.process(itemRead);
            if (processedItem == null) continue;

            // 3. Write
            itemWriter.write(processedItem);

        }
    }

}

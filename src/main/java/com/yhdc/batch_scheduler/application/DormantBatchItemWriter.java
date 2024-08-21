package com.yhdc.batch_scheduler.application;

import com.yhdc.batch_scheduler.batch.ItemWriter;
import com.yhdc.batch_scheduler.entity.Customer;
import com.yhdc.batch_scheduler.provider.EmailProvider;
import com.yhdc.batch_scheduler.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchItemWriter implements ItemWriter<Customer> {

    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public DormantBatchItemWriter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void write(Customer item) {
        customerRepository.save(item);
        emailProvider.sendEmail(item.getEmail(), "Dormant status email.", "Do not reply.");
    }

}

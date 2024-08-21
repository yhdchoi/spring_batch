package com.yhdc.batch_scheduler.application;

import com.yhdc.batch_scheduler.batch.ItemReader;
import com.yhdc.batch_scheduler.entity.Customer;
import com.yhdc.batch_scheduler.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchItemReader implements ItemReader<Customer> {

    private int pageNo = 0;
    private final CustomerRepository customerRepository;

    public DormantBatchItemReader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer read() {
        final PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by(Sort.Direction.ASC, "id"));
        final Page<Customer> page = customerRepository.findAll(pageRequest);

        if (page.isEmpty()) {
            pageNo = 0;
            return null;
        } else {
            pageNo++;
            return page.getContent().getFirst();
        }
    }

}

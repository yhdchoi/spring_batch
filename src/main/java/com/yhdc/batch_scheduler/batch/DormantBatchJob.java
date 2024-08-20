package com.yhdc.batch_scheduler.batch;

import com.yhdc.batch_scheduler.entity.Customer;
import com.yhdc.batch_scheduler.provider.EmailProvider;
import com.yhdc.batch_scheduler.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class DormantBatchJob {

    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public DormantBatchJob(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.emailProvider = new EmailProvider.Fake();
    }


    public JobExecution execute() {

        int pageNo = 0;

        while (true) {
            // 1. Look up customer
            final PageRequest pageRequest = PageRequest.of(pageNo, 1, Sort.by(Sort.Direction.ASC, "id"));
            final Page<Customer> page = customerRepository.findAll(pageRequest);

            final Customer customer;
            if (page.isEmpty()) {
                break;
            } else {
                pageNo++;
                customer = page.getContent().getFirst();
            }

            // 2. Check for dormant account
            final boolean isDormantTarget = LocalDate.now()
                    .minusDays(365)
                    .isAfter(customer.getLoginAt().toLocalDate());

            if (isDormantTarget) {
                customer.setStatus(Customer.Status.DORMANT);
            } else {
                continue;
            }

            // 3. Change account to dormant status
            customerRepository.save(customer);

            // 4. Send mail
            emailProvider.sendEmail(customer.getEmail(), "Dormant status email.", "Do not reply.");
        }

        return null;

    }


}

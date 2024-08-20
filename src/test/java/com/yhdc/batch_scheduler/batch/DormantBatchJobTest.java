package com.yhdc.batch_scheduler.batch;

import com.yhdc.batch_scheduler.entity.Customer;
import com.yhdc.batch_scheduler.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class DormantBatchJobTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DormantBatchJob dormantBatchJob;


    @BeforeEach
    public void cleanup() {
        customerRepository.deleteAll();
    }


    @Test
    @DisplayName("There are three customers who haven't logged in for over a year and 5 customers who has logged in within a year")
    void test1() {

        // Given
        saveCustomer(366);
        saveCustomer(366);
        saveCustomer(366);
        saveCustomer(364);
        saveCustomer(364);
        saveCustomer(364);
        saveCustomer(364);
        saveCustomer(364);

        // When
        dormantBatchJob.execute();

        // Then
        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(customer -> customer.getStatus() == Customer.Status.DORMANT)
                .count();

        Assertions.assertEquals(3, dormantCount);

    }


    @Test
    @DisplayName("There are 10 customers and are not for dormant state")
    void test2() {

        // Given
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(1);
        saveCustomer(2);
        saveCustomer(3);
        saveCustomer(2);
        saveCustomer(5);
        saveCustomer(2);

        // When
        dormantBatchJob.execute();

        // Then
        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(customer -> customer.getStatus() == Customer.Status.DORMANT)
                .count();

        Assertions.assertEquals(0, dormantCount);

    }

    @Test
    @DisplayName("The batch should be working even if there are no customers")
    void test3() {

        // When
        dormantBatchJob.execute();

        // Then
        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(customer -> customer.getStatus() == Customer.Status.DORMANT)
                .count();

        Assertions.assertEquals(0, dormantCount);

    }


    private void saveCustomer(long minusDays) {
        final Customer test = new Customer("test_" + minusDays, "test_" + minusDays + "@test.com");
        test.setLoginAt(LocalDateTime.now().minusDays(minusDays));
        customerRepository.save(test);
    }
}
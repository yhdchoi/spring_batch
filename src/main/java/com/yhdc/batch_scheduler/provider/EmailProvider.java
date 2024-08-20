package com.yhdc.batch_scheduler.provider;

import lombok.extern.slf4j.Slf4j;

public interface EmailProvider {

    void sendEmail(String to, String subject, String body);

    @Slf4j
    class Fake implements EmailProvider {
        @Override
        public void sendEmail(String to, String subject, String body) {
          log.info("{} email has been sent! {} : {}", to, subject, body);
        }
    }

}

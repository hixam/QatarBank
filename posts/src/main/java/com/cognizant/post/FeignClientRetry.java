package com.cognizant.post;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignClientRetry implements Retryer {
    @Override
    public void continueOrPropagate(RetryableException e) {
        try {
            log.info("retrying feign client request !");
            Thread.sleep(3000L);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }

    @Override
    public Retryer clone() {
        return new FeignClientRetry();
    }
}

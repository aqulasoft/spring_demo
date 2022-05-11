package com.example.test212.services.impl;

import com.example.test212.services.AuctionScheduler;
import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AuctionSchedulerImpl implements AuctionScheduler {

    @Getter
    private long now;

    @Scheduled(cron = "0 * * ? * *")
    public void scheduleTaskUsingCronExpression() {
        updateTime();
        System.out.println(
                "Fixed delay task 1- " + System.currentTimeMillis() / 1000);
    }

    private void updateTime() {
        now = System.currentTimeMillis();
    }
}

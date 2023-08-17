package com.addy.app.service;

import com.addy.app.entity.FraudCheckHistory;
import com.addy.app.repository.FraudCheckHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record FraudCheckService(FraudCheckHistoryRepository repository) {

    public boolean isFraudulentCustomer(Integer customerId){

        repository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(true)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return false;
    }
}

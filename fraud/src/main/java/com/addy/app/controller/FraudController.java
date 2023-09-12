package com.addy.app.controller;

import com.addy.app.service.FraudCheckService;
import module.FraudCheckResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check/")
public record FraudController(FraudCheckService service) {
    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraudster = service.isFraudulentCustomer(customerId);

        return new FraudCheckResponse(isFraudster);
    }
}

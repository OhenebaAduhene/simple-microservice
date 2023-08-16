package com.addy.app.service;

import com.addy.app.entity.Customer;
import com.addy.app.model.CustomerRegistrationRequest;
import com.addy.app.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository repository) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check if email valid
        //todo: check if email is not taken
        //todo: store customer in db

        repository.save(customer);
    }
}

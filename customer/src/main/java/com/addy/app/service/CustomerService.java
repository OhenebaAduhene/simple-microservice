package com.addy.app.service;

import com.addy.app.NotificationMessageProducer;
import com.addy.app.entity.Customer;
import com.addy.app.fraud.FraudClient;
import com.addy.app.model.CustomerRegistrationRequest;
import com.addy.app.notification.NotificationClient;
import com.addy.app.notification.NotificationRequest;
import com.addy.app.repository.CustomerRepository;
import com.addy.app.fraud.FraudCheckResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository repository,
                              FraudClient fraudClient,
                              NotificationClient notificationClient,
                              NotificationMessageProducer notificationMessageProducer
) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        repository.saveAndFlush(customer);
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(),
                customer.getEmail(),
                "hello"
        );
        notificationMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );


    }
}

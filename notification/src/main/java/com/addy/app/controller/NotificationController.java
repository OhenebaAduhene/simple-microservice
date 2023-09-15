package com.addy.app.controller;

import com.addy.app.notification.NotificationRequest;
import com.addy.app.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/notification/")
public record NotificationController(NotificationService service) {

    @PostMapping()
    public void notificationRequest(@RequestBody NotificationRequest notificationRequest){
        log.info("Sending out notification request {}", notificationRequest);
        service.send(notificationRequest);
    }
}

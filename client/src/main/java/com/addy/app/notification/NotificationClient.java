package com.addy.app.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "notification",
        url = "${client.notification.url}"
)
public interface NotificationClient {
    @PostMapping("/api/v1/notification/")
    NotificationRequest notificationRequest(@RequestBody NotificationRequest notificationRequest);
}

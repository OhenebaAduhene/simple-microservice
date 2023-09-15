package com.addy.app.notification;

public record NotificationRequest(Integer toCustomerId, String toCustomerEmail, String message) { }

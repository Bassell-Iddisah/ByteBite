package com.bytebite.notification_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "Notification Service running...";
    }
}

package com.example.jobproject.controller;

import com.example.jobproject.entity.SmsNotification;
import com.example.jobproject.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    // ✅ Simulate sending an SMS
    @PostMapping("/send")
    public ResponseEntity<SmsNotification> sendSms(
            @RequestParam String phoneNumber,
            @RequestParam String message,
            @RequestParam(required = false, defaultValue = "GENERAL") String eventType) {
        return ResponseEntity.ok(smsService.sendSms(phoneNumber, message, eventType));
    }

    // ✅ Get all SMS logs
    @GetMapping("/all")
    public ResponseEntity<List<SmsNotification>> getAllSms() {
        return ResponseEntity.ok(smsService.getAllSms());
    }

    // ✅ Get a specific SMS record by ID
    @GetMapping("/{id}")
    public ResponseEntity<SmsNotification> getSmsById(@PathVariable Long id) {
        return ResponseEntity.ok(smsService.getSmsById(id));
    }
}


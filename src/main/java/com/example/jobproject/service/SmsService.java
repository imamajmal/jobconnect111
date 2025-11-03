package com.example.jobproject.service;

import com.example.jobproject.entity.SmsNotification;
import java.util.List;

public interface SmsService {
    SmsNotification sendSms(String phoneNumber, String message, String eventType);
    List<SmsNotification> getAllSms();
    SmsNotification getSmsById(Long id);
}


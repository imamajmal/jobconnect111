package com.example.jobproject.service.impl;

import com.example.jobproject.entity.SmsNotification;
import com.example.jobproject.repository.SmsNotificationRepository;
import com.example.jobproject.service.SmsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SmsServiceImpl implements SmsService {

    private final SmsNotificationRepository smsRepository;

    public SmsServiceImpl(SmsNotificationRepository smsRepository) {
        this.smsRepository = smsRepository;
    }

    @Override
    public SmsNotification sendSms(String phoneNumber, String message, String eventType) {
        SmsNotification sms = SmsNotification.builder()
                .recipientPhone(phoneNumber)
                .message(message)
                .eventType(eventType)
                .status("SENT") // always SENT (simulation)
                .sentAt(LocalDateTime.now())
                .build();

        // Save SMS record to DB
        return smsRepository.save(sms);
    }

    @Override
    public List<SmsNotification> getAllSms() {
        return smsRepository.findAll();
    }

    @Override
    public SmsNotification getSmsById(Long id) {
        return smsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SMS not found with id: " + id));
    }
}


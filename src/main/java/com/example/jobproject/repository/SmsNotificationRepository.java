package com.example.jobproject.repository;

import com.example.jobproject.entity.SmsNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsNotificationRepository extends JpaRepository<SmsNotification, Long> {
}



package com.example.jobproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipientPhone;     // phone number
    private String message;            // message body
    private String status;             // SENT / FAILED
    private String eventType;          // e.g., Job Update, Registration, Alert

    private LocalDateTime sentAt;
}


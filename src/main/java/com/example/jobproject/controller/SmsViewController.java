package com.example.jobproject.controller;

import com.example.jobproject.entity.SmsNotification;
import com.example.jobproject.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sms")
public class SmsViewController {

    private final SmsService smsService;

    // ✅ View all SMS records
    @GetMapping
    public String viewAllSms(Model model) {
        List<SmsNotification> smsList = smsService.getAllSms();
        model.addAttribute("smsList", smsList);
        return "sms-list";  // View page
    }

    // ✅ Show Send SMS form
    @GetMapping("/send")
    public String showSendSmsForm(Model model) {
        model.addAttribute("sms", new SmsNotification());
        return "sms-send";  // Form page
    }

    // ✅ Handle SMS form submission
    @PostMapping("/send")
    public String sendSms(@ModelAttribute("sms") SmsNotification sms) {
        smsService.sendSms(
                sms.getRecipientPhone(),
                sms.getMessage(),
                sms.getEventType()
        );
        return "redirect:/sms";  // redirect to list after saving
    }

    // ✅ Optional: View single SMS detail
    @GetMapping("/{id}")
    public String viewSmsDetails(@PathVariable Long id, Model model) {
        SmsNotification sms = smsService.getSmsById(id);
        model.addAttribute("sms", sms);
        return "sms-details";
    }
}


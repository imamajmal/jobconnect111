package com.example.jobproject.controller;

import com.example.jobproject.entity.Job;
import com.example.jobproject.entity.User;
import com.example.jobproject.repository.JobRepository;
import com.example.jobproject.repository.UserRepository;
import com.example.jobproject.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seeker")
public class JobSeekerController {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final JobApplicationService jobApplicationService;

    // ✅ Show all jobs (for seekers)
    @GetMapping("/jobs")
    public String viewAllJobs(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "jobs-view"; // → jobs-view.html
    }

    // ✅ Apply for a job
    @PostMapping("/apply/{jobId}")
    public String applyForJob(@PathVariable Long jobId, Authentication authentication) {
        String seekerEmail = authentication.getName();
        User seeker = userRepository.findByEmail(seekerEmail).orElseThrow();
        Job job = jobRepository.findById(jobId).orElseThrow();
        jobApplicationService.applyForJob(job, seeker);
        return "redirect:/seeker/applied-jobs";
    }

    // ✅ View applied jobs
    @GetMapping("/applied-jobs")
    public String viewAppliedJobs(Authentication authentication, Model model) {
        String seekerEmail = authentication.getName();
        User seeker = userRepository.findByEmail(seekerEmail).orElseThrow();
        model.addAttribute("applications", jobApplicationService.getApplicationsBySeeker(seeker));
        return "applied-jobs"; // → applied-jobs.html
    }
}


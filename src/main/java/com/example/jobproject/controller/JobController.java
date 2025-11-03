package com.example.jobproject.controller;

import com.example.jobproject.entity.Job;
import com.example.jobproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // ✅ EMPLOYER adds a job
    @PostMapping("/add")
    public ResponseEntity<Job> addJob(@RequestBody Job job, Authentication authentication) {
        String employerUsername = authentication.getName();
        return ResponseEntity.ok(jobService.addJob(job, employerUsername));
    }

    // ✅ EMPLOYER updates a job
    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job, Authentication authentication) {
        String employerUsername = authentication.getName();
        return ResponseEntity.ok(jobService.updateJob(id, job, employerUsername));
    }

    // ✅ EMPLOYER deletes a job
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id, Authentication authentication) {
        String employerUsername = authentication.getName();
        jobService.deleteJob(id, employerUsername);
        return ResponseEntity.ok("Job deleted successfully");
    }

    // ✅ Everyone (including job seekers) can see all jobs
    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // ✅ EMPLOYER can view their own jobs
    @GetMapping("/my-jobs")
    public ResponseEntity<List<Job>> getEmployerJobs(Authentication authentication) {
        String employerUsername = authentication.getName();
        return ResponseEntity.ok(jobService.getEmployerJobs(employerUsername));
    }
}


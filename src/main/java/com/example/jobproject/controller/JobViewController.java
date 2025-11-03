package com.example.jobproject.controller;

import com.example.jobproject.entity.Job;
import com.example.jobproject.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobViewController {

    private final JobService jobService;

    // ✅ 1️⃣ View All Jobs (for Job Seekers)
    @GetMapping("/all")
    public String viewAllJobs(Model model) {
        List<Job> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);
        return "jobs-list";
    }

    // ✅ 2️⃣ View Employer’s Own Jobs
    @GetMapping("/employer")
    public String viewEmployerJobs(Authentication authentication, Model model) {
        String employerEmail = authentication.getName(); // ✅ Get email instead of username
        List<Job> jobs = jobService.getEmployerJobs(employerEmail);
        model.addAttribute("jobs", jobs);
        model.addAttribute("email", employerEmail);
        return "employer-jobs";
    }

    // ✅ 3️⃣ Show Add Job Form
    @GetMapping("/add")
    public String showAddJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "add-job";
    }

    // ✅ 4️⃣ Save a new Job
    @PostMapping("/add")
    public String addJob(@ModelAttribute("job") Job job, Authentication authentication) {
        String employerEmail = authentication.getName(); // ✅ Use email here
        jobService.addJob(job, employerEmail);
        return "redirect:/jobs/employer";
    }

    // ✅ 5️⃣ Show Edit Job Form
    @GetMapping("/edit/{id}")
    public String showEditJobForm(@PathVariable Long id, Model model) {
        Job job = jobService.getAllJobs().stream()
                .filter(j -> j.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("job", job);
        return "edit-job";
    }

    // ✅ 6️⃣ Update Job
    @PostMapping("/update/{id}")
    public String updateJob(@PathVariable Long id, @ModelAttribute("job") Job updatedJob, Authentication authentication) {
        String employerEmail = authentication.getName(); // ✅ Use email here
        jobService.updateJob(id, updatedJob, employerEmail);
        return "redirect:/jobs/employer";
    }

    // ✅ 7️⃣ Delete Job
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id, Authentication authentication) {
        String employerEmail = authentication.getName(); // ✅ Use email here
        jobService.deleteJob(id, employerEmail);
        return "redirect:/jobs/employer";
    }
}



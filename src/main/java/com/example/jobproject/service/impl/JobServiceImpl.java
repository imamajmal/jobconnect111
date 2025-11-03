package com.example.jobproject.service.impl;

import com.example.jobproject.entity.Job;
import com.example.jobproject.entity.User;
import com.example.jobproject.repository.JobRepository;
import com.example.jobproject.repository.UserRepository;
import com.example.jobproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Job addJob(Job job, String employerEmail) {
        // ✅ Use Email instead of Username (fix)
        User employer = userRepository.findByEmail(employerEmail)
                .orElseThrow(() -> new RuntimeException("Employer not found with email: " + employerEmail));

        job.setEmployer(employer);
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Long jobId, Job updatedJob, String employerEmail) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (!job.getEmployer().getEmail().equals(employerEmail)) {
            throw new RuntimeException("Unauthorized to edit this job");
        }

        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setLocation(updatedJob.getLocation());
        job.setSalary(updatedJob.getSalary());
        job.setDeadline(updatedJob.getDeadline());

        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long jobId, String employerEmail) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (!job.getEmployer().getEmail().equals(employerEmail)) {
            throw new RuntimeException("Unauthorized to delete this job");
        }

        jobRepository.delete(job);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getEmployerJobs(String employerEmail) {
        User employer = userRepository.findByEmail(employerEmail)
                .orElseThrow(() -> new RuntimeException("Employer not found with email: " + employerEmail));
        return jobRepository.findByEmployer(employer);
    }
}



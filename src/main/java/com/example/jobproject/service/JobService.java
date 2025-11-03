package com.example.jobproject.service;

import com.example.jobproject.entity.Job;
import java.util.List;

public interface JobService {
    Job addJob(Job job, String employerUsername);
    Job updateJob(Long jobId, Job updatedJob, String employerUsername);
    void deleteJob(Long jobId, String employerUsername);
    List<Job> getAllJobs(); // visible to everyone
    List<Job> getEmployerJobs(String employerUsername); // employer's own jobs
}

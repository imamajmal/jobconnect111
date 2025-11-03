package com.example.jobproject.service;

import com.example.jobproject.entity.JobApplication;
import com.example.jobproject.entity.Job;
import com.example.jobproject.entity.User;
import java.util.List;

public interface JobApplicationService {
    JobApplication applyForJob(Job job, User seeker);
    List<JobApplication> getApplicationsBySeeker(User seeker);
}


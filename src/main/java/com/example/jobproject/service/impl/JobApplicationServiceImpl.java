package com.example.jobproject.service.impl;

import com.example.jobproject.entity.Job;
import com.example.jobproject.entity.JobApplication;
import com.example.jobproject.entity.User;
import com.example.jobproject.repository.JobApplicationRepository;
import com.example.jobproject.repository.JobRepository;
import com.example.jobproject.repository.UserRepository;
import com.example.jobproject.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Override
    public JobApplication applyForJob(Job job, User seeker) {
        JobApplication application = JobApplication.builder()
                .job(job)
                .seeker(seeker)
                .status("APPLIED")
                .build();
        return jobApplicationRepository.save(application);
    }

    @Override
    public List<JobApplication> getApplicationsBySeeker(User seeker) {
        return jobApplicationRepository.findBySeeker(seeker);
    }
}

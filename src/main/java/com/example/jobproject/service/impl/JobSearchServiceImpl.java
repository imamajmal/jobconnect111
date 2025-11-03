package com.example.jobproject.service.impl;

import com.example.jobproject.entity.Job;
import com.example.jobproject.repository.JobRepository;
import com.example.jobproject.service.JobSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSearchServiceImpl implements JobSearchService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));
    }

    @Override
    public List<Job> searchByKeyword(String keyword) {
        return jobRepository.searchByKeyword(keyword);
    }

    @Override
    public List<Job> searchByLocation(String location) {
        return jobRepository.findByLocationContainingIgnoreCase(location);
    }

    @Override
    public List<Job> searchByKeywordAndLocation(String keyword, String location) {
        return jobRepository.searchByKeywordAndLocation(keyword, location);
    }
}


package com.example.jobproject.service;

import com.example.jobproject.entity.Job;
import java.util.List;

public interface JobSearchService {
    List<Job> getAllJobs();
    Job getJobById(Long jobId);
    List<Job> searchByKeyword(String keyword);
    List<Job> searchByLocation(String location);
    List<Job> searchByKeywordAndLocation(String keyword, String location);
}


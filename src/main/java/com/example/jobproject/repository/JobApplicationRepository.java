package com.example.jobproject.repository;

import com.example.jobproject.entity.JobApplication;
import com.example.jobproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findBySeeker(User seeker);
}


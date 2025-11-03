package com.example.jobproject.repository;

import com.example.jobproject.entity.Job;
import com.example.jobproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployer(User employer);

     List<Job> findByEmployerEmail(String employerEmail);



    // ✅ Find by location
    List<Job> findByLocationContainingIgnoreCase(String location);

    // ✅ Find by title or description (keyword search)
    @Query("SELECT j FROM Job j WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
        "OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Job> searchByKeyword(String keyword);

    // ✅ Combined search: keyword + location
    @Query("SELECT j FROM Job j WHERE " +
        "(LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
        "OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
        "AND LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))")
    List<Job> searchByKeywordAndLocation(String keyword, String location);
}


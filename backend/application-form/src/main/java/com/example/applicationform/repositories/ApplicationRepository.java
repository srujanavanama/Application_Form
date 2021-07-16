package com.example.applicationform.repositories;

import com.example.applicationform.dao.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Application findByApplicationId(Integer applicationId);
    List<Application> findByApplicationStatus(String status);
}

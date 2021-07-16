package com.example.applicationform.service;

import com.example.applicationform.dao.Applicant;
import com.example.applicationform.dao.Application;
import com.example.applicationform.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public List<Application> getActiveApplications() {
        return applicationRepository.findByApplicationStatus("active");
    }

    public Application getApplicationById(Integer applicationId) {
        return applicationRepository.findByApplicationId(applicationId);
    }

    public Application saveApplication(String applicationType, Integer amount, String applicationStatus, Set<Applicant> applicants) {
        return applicationRepository.save(new Application(applicationType, amount, applicationStatus, applicants));
    }

    public void deleteApplication(Integer applicationId) {
        applicationRepository.deleteById(applicationId);
//        Application application = applicationRepository.findByApplicationId(applicationId);
//        application.setApplicationStatus("inactive");
    }
}

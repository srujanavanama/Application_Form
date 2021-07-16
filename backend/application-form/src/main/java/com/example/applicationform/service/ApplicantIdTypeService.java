package com.example.applicationform.service;

import com.example.applicationform.dao.ApplicantIdType;
import com.example.applicationform.repositories.ApplicantIdTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantIdTypeService {
    @Autowired
    private ApplicantIdTypeRepository applicantIdTypeRepository;

    public ApplicantIdTypeService(ApplicantIdTypeRepository applicantIdTypeRepository) {
        this.applicantIdTypeRepository = applicantIdTypeRepository;
    }

    public List<ApplicantIdType> getAllApplicantIdTypes() {
        return applicantIdTypeRepository.findAll();
    }

    public ApplicantIdType getApplicantIdTypeByName(String idTypeName) {
        return applicantIdTypeRepository.findByApplicantIdTypeName(idTypeName);
    }
}

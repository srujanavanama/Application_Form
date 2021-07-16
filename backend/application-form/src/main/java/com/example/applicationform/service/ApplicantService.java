package com.example.applicationform.service;

import com.example.applicationform.dao.Applicant;
import com.example.applicationform.dao.ApplicantIdType;
import com.example.applicationform.dao.Application;
import com.example.applicationform.repositories.ApplicantIdTypeRepository;
import com.example.applicationform.repositories.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ApplicantIdTypeRepository applicantIdTypeRepository;

    public ApplicantService(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public Applicant saveApplicant(Integer applicantId, String firstName, String lastName, String gender, List<String>applicantIdTypeNameList) {
        List<ApplicantIdType> applicantIdTypesList = new ArrayList<>();
        applicantIdTypeNameList.forEach(name -> {
            ApplicantIdType idType = applicantIdTypeRepository.findByApplicantIdTypeName(name);
            if(idType == null) {
                applicantIdTypesList.add(applicantIdTypeRepository.save(new ApplicantIdType(name)));
            } else {
                applicantIdTypesList.add(idType);
            }
        });
        Set applicantIdTypes = Set.copyOf(applicantIdTypesList);
        return applicantRepository.save(new Applicant(applicantId, firstName, lastName, gender, applicantIdTypes));
    }
}

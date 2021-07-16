package com.example.applicationform.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ApplicantIdType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id_type_number")
    private Integer applicantIdTypeNumber;

    @Column(name = "applicant_id_type_name")
    private String applicantIdTypeName;

    @ManyToMany(mappedBy = "applicantIdTypes")
    private Set<Applicant> applicantsForIdType = new HashSet<>();

    protected ApplicantIdType() {

    }

    public ApplicantIdType(String applicantIdTypeName) {
        this.applicantIdTypeName = applicantIdTypeName;
    }

    public Integer getApplicantIdTypeNumber() {
        return applicantIdTypeNumber;
    }

    public void setApplicantIdTypeNumber(Integer applicantIdTypeNumber) {
        this.applicantIdTypeNumber = applicantIdTypeNumber;
    }

    public String getApplicantIdTypeName() {
        return applicantIdTypeName;
    }

    public void setApplicantIdTypeName(String applicantIdTypeName) {
        this.applicantIdTypeName = applicantIdTypeName;
    }

    public Set<Applicant> getApplicantsForIdType() {
        return applicantsForIdType;
    }

    public void setApplicantsForIdType(Set<Applicant> applicantsForIdType) {
        this.applicantsForIdType = applicantsForIdType;
    }
}

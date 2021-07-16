package com.example.applicationform.web;

import java.util.List;

public class ApplicationDto {
    private Integer applicationId;
    private String applicationType;
    private Integer amount;
    private String applicationStatus;
    private List<ApplicantDto> applicants;

    public ApplicationDto(Integer applicationId, String applicationType, Integer amount, String applicationStatus, List<ApplicantDto> applicants) {
        this.applicationId = applicationId;
        this.applicationType = applicationType;
        this.amount = amount;
        this.applicationStatus = applicationStatus;
        this.applicants = applicants;
    }

    protected ApplicationDto() {

    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String status) {
        this.applicationStatus = applicationStatus;
    }

    public List<ApplicantDto> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<ApplicantDto> applicants) {
        this.applicants = applicants;
    }
}

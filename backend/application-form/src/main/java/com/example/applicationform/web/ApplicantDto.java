package com.example.applicationform.web;

import java.util.List;

public class ApplicantDto {
    private Integer applicantId;
    private String firstName;
    private String lastName;
    private String gender;
    private List<String> applicantIdTypes;

    public ApplicantDto(Integer applicantId, String firstName, String lastName, String gender, List<String> applicantIdTypes) {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.applicantIdTypes = applicantIdTypes;
    }

    protected ApplicantDto() {

    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getApplicantIdTypes() {
        return applicantIdTypes;
    }

    public void setApplicantIdTypes(List<String> applicantIdTypes) {
        this.applicantIdTypes = applicantIdTypes;
    }
}

package com.example.applicationform.dao;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Applicant {
    @Id
    @Column(name = "applicant_id")
    private Integer applicantId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @ManyToMany(mappedBy = "applicants")
    private Set<Application> applications = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "applicant_id",
            joinColumns = @JoinColumn(name = "applicant_id"),
            inverseJoinColumns = @JoinColumn(name = "applicant_id_type_number")
    )
    private Set<ApplicantIdType> applicantIdTypes;

    protected Applicant() {

    }

    public Applicant(Integer applicantId, String firstName, String lastName, String gender, Set<ApplicantIdType> applicantIdTypes) {
        this.applicantId = applicantId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.applicantIdTypes = applicantIdTypes;
        this.applicantIdTypes.forEach(applicantIdType -> applicantIdType.getApplicantsForIdType().add(this));
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

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public Set<ApplicantIdType> getApplicantIdTypes() {
        return applicantIdTypes;
    }

    public void setApplicantIdTypes(Set<ApplicantIdType> applicantIdTypes) {
        this.applicantIdTypes = applicantIdTypes;
    }
}

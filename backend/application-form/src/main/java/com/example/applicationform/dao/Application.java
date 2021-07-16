package com.example.applicationform.dao;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "application_type")
    private String applicationType;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "application_status")
    private String applicationStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="application_applicant",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "applicant_id")
    )
    private Set<Applicant> applicants;

    protected Application() {

    }

    public Application(String applicationType, Integer amount, String applicationStatus, Set<Applicant> applicants) {
        this.applicationType = applicationType;
        this.amount = amount;
        this.applicationStatus = applicationStatus;
        this.applicants = applicants;
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

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Set<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(Set<Applicant> applicants) {
        this.applicants = applicants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return Objects.equals(applicationId, that.applicationId) &&
                Objects.equals(applicationType, that.applicationType) &&
                Objects.equals(applicationStatus, that.applicationStatus) &&
                Objects.equals(applicants, that.applicants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, applicationType, applicationStatus);
    }
}

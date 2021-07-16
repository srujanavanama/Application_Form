package com.example.applicationform.web;

import com.example.applicationform.dao.Applicant;
import com.example.applicationform.dao.ApplicantIdType;
import com.example.applicationform.dao.Application;
import com.example.applicationform.service.ApplicantIdTypeService;
import com.example.applicationform.service.ApplicantService;
import com.example.applicationform.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private ApplicantIdTypeService applicantIdTypeService;

    public ApplicationController(ApplicationService applicationService, ApplicantService applicantService, ApplicantIdTypeService applicantIdTypeService) {
        this.applicationService = applicationService;
        this.applicantService = applicantService;
        this.applicantIdTypeService = applicantIdTypeService;
    }

    protected ApplicationController() {

    }

    @GetMapping(path = "/idTypes")
    public List<String> getAllApplicantIdTypes() {
        List<ApplicantIdType> allApplicantIdTypes = applicantIdTypeService.getAllApplicantIdTypes();
        return allApplicantIdTypes.stream().map(ApplicantIdType::getApplicantIdTypeName).collect(Collectors.toList());
    }

    @GetMapping
    public List<ApplicationDto> getAllApplications() {
        List<Application> allApplications = applicationService.getAllApplications();
        List<ApplicationDto> allApplicationDtos = allApplications.stream().map(this::toApplicationDto).collect(Collectors.toList());
        return  allApplicationDtos;
    }

    @GetMapping(path = "/active")
    public List<ApplicationDto> getActiveApplications() {
        List<Application> allApplications = applicationService.getActiveApplications();
        List<ApplicationDto> allApplicationDtos = allApplications.stream().map(this::toApplicationDto).collect(Collectors.toList());
        return  allApplicationDtos;
    }

    @GetMapping(path = "/{applicationId}")
    public ApplicationDto getApplication(@PathVariable("applicationId") Integer applicationId) {
        Application application = applicationService.getApplicationById(applicationId);
        return toApplicationDto(application);
    }

    @PostMapping
    public ResponseEntity<ApplicationDto> submitApplication(@RequestBody ApplicationDto applicationDto) {
        List<ApplicantDto> applicantDtos = applicationDto.getApplicants();
        Set<Applicant> applicants = applicantDtos.stream().map(applicantDto -> applicantService.saveApplicant(applicantDto.getApplicantId(),
                applicantDto.getFirstName(), applicantDto.getLastName(), applicantDto.getGender(), applicantDto.getApplicantIdTypes())).collect(Collectors.toSet());
        Application submittedApplication = applicationService.saveApplication(applicationDto.getApplicationType(), applicationDto.getAmount(), applicationDto.getApplicationStatus(), applicants);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{applicationId}").buildAndExpand(submittedApplication.getApplicationId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(path = "/{applicationId}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Integer applicationId) {
        applicationService.deleteApplication(applicationId);
        return ResponseEntity.noContent().build();
    }

    private ApplicationDto toApplicationDto(Application application) {
        Set<Applicant> allApplicants = application.getApplicants();
        List<ApplicantDto> allApplicantDtos = allApplicants.stream().map(this::toApplicantDto).collect(Collectors.toList());
        return new ApplicationDto(application.getApplicationId(), application.getApplicationType(), application.getAmount(), application.getApplicationStatus(), allApplicantDtos);
    }

    private ApplicantDto toApplicantDto(Applicant applicant) {
        Set<ApplicantIdType> idTypes = applicant.getApplicantIdTypes();
        List<String> idTypesNames = idTypes.stream().map(ApplicantIdType::getApplicantIdTypeName).collect(Collectors.toList());
        return new ApplicantDto(applicant.getApplicantId(), applicant.getFirstName(), applicant.getLastName(), applicant.getGender(), idTypesNames);
    }

    private Application toApplicationEntity(ApplicationDto applicationDto) {
        List<ApplicantDto> applicantDtos = applicationDto.getApplicants();
        Set<Applicant> applicants = applicantDtos.stream().map(this::toApplicantEntity).collect(Collectors.toSet());
        return new Application(applicationDto.getApplicationType(), applicationDto.getAmount(), applicationDto.getApplicationStatus(), applicants);
    }

    private Applicant toApplicantEntity(ApplicantDto applicantDto) {
        List<String> idTypesNames = applicantDto.getApplicantIdTypes();
        Set<ApplicantIdType> idTypes = idTypesNames.stream().map(name -> applicantIdTypeService.getApplicantIdTypeByName(name)).collect(Collectors.toSet());
        return new Applicant(applicantDto.getApplicantId(), applicantDto.getFirstName(), applicantDto.getLastName(), applicantDto.getGender(), idTypes);
    }
}

package com.example.applicationform.repositories;

import com.example.applicationform.dao.ApplicantIdType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantIdTypeRepository extends JpaRepository<ApplicantIdType, Integer> {
//    @EntityGraph(attributePaths = "applicantIdTypes")
    ApplicantIdType findByApplicantIdTypeName(@Param("applicant_id_type_name") String applicantIdTypeName);
}

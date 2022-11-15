package com.healthconnect.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthconnect.user.model.common.MedicalCollegeMaster;
import com.healthconnect.user.model.common.SimpleDto;

@Repository
public interface MedicalCollegeRepository extends JpaRepository<MedicalCollegeMaster, Long> {
	
	@Query("SELECT new com.healthconnect.user.model.common.SimpleDto(c.recordId, c.name) FROM MedicalCollegeMaster c "
			+ " where c.name like %:collegeName% ")
	List<SimpleDto> fetchMedicalColleges(@Param("collegeName") String collegeName);

    MedicalCollegeMaster findByName(String collegeName);
}

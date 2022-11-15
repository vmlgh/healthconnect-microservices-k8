package com.healthconnect.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.healthconnect.user.model.common.DegreeMaster;
import com.healthconnect.user.model.common.SimpleDto;

@Repository
public interface MedicalDegreeRepository extends JpaRepository<DegreeMaster, Long> {

	@Query("SELECT new com.healthconnect.user.model.common.SimpleDto(d.recordId, d.name) FROM DegreeMaster d")
	List<SimpleDto> fetchMedicalDegrees();

	@Nullable
	DegreeMaster findByName(String name);
}


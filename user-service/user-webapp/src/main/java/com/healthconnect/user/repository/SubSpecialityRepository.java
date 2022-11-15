package com.healthconnect.user.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.healthconnect.user.model.common.CommonDto;
import com.healthconnect.user.model.common.SubSpecialityMaster;

public interface SubSpecialityRepository extends JpaRepository<SubSpecialityMaster, Long> {
	
	@Query("SELECT new com.healthconnect.user.model.common.CommonDto(ssp.name, ssp.description) FROM SubSpecialityMaster ssp WHERE ssp.specialityMaster.name = :name")
	Set<CommonDto> findBySpecialityId(@Param("name") String name);
}

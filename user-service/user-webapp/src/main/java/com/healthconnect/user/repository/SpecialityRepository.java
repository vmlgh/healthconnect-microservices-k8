package com.healthconnect.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthconnect.user.model.common.CommonDto;
import com.healthconnect.user.model.common.SpecialityMaster;

@Repository
public interface SpecialityRepository extends JpaRepository<SpecialityMaster, Long> {

    @Query("SELECT new com.healthconnect.user.model.common.CommonDto(s.name, s.description) FROM SpecialityMaster s")
    List<CommonDto> fetchSpeciality();

    @Query("SELECT sp from SpecialityMaster sp WHERE sp.name = :name")
    SpecialityMaster findByName(@Param("name") String name);
    
	/*
	 * @Query("SELECT new com.healthconnect.platform.dto.common.CommonDto(ss.name, ss.description) FROM SubSpecialityMaster ss, SpecialityMaster sm WHERE sm.name = :name"
	 * ) Set<CommonDto> findSubSpeciality(@Param("name") String name);
	 */
}


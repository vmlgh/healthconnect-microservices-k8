package com.healthconnect.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import com.healthconnect.user.model.physician.Physician;

/**
 * @author aswain
 *
 */
public interface PhysicianRepository  extends JpaRepository<Physician, Long>{

    @Nullable
    @Query("SELECT p from Physician p where p.user.userId = :userId")
    Physician findPhysicianByUserId(@Param("userId") String userId);

    @Nullable
    @Query("SELECT p from Physician p where p.user.recordId = :recordId")
    Physician findPhysicianByUserRecordId(@Param("recordId") long recordId);

	Physician findByRegistrationNumber(String regdNo);
	
	@Nullable
	@Query("SELECT p from Physician p where p.speciality.name = :specName")
	List<Physician> findBySpecName(@Param("specName") String specName);
    
}


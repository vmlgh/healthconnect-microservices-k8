package com.healthconnect.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import com.healthconnect.user.model.physician.PhysicianPricing;

public interface PhysicianPricingRepository extends JpaRepository<PhysicianPricing, Long> {
	
	@Nullable
	@Query("SELECT p from PhysicianPricing p where p.physician.recordId = :recordId")
	PhysicianPricing findPhysicianPricingByPhysicianRecordId(@Param("recordId") long recordId);
	
}
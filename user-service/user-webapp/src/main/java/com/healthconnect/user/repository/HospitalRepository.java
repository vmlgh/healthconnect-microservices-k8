package com.healthconnect.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthconnect.user.model.physician.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	
	@Query("SELECT h from Hospital h where h.deleted = :deleted AND h.name = :name AND h.address.pinCode = :pinCode")
    Hospital findByNameAndPinCode(@Param("name") String name, @Param("pinCode") String pinCode, @Param("deleted") boolean delete);

}

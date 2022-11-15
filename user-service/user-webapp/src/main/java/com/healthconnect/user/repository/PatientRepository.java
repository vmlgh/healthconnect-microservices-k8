package com.healthconnect.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.patient.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	 @Nullable
	 @Query("SELECT pt from Patient pt where pt.user.recordId = :recordId")
	 Patient findByUserRecordId(@Param("recordId") long userId);
	 
	 Patient findPatientByUser(User user);
	 
	 @Modifying
	 @Query(value = "UPDATE Patient pt SET pt.Name = :name, pt.Age= :age, pt.Weight = :weight, pt.BloodGroup = :bloodGroup, pt.Gender = :gender, pt.Address.recordId = :addressId WHERE pt.RecordId = :id ", nativeQuery = true)
	 void updatePatient(@Param("name") String name, @Param("age") int age, @Param("weight")double weight, @Param("bloodGroup")String bloodGroup, @Param("gender")String gender, @Param("addressId") long addressId, @Param("id")long recordId);
}


package com.healthconnect.user.controller;

import java.util.List;

import com.healthconnect.user.model.physician.EducationalQualificationDto;
import com.healthconnect.user.model.physician.Physician;
import com.healthconnect.user.model.physician.PhysicianDto;
import com.healthconnect.user.model.physician.PhysicianHospitalDto;
import com.healthconnect.user.model.physician.PhysicianProfileDto;

public interface PhysicianService {

	PhysicianDto createDoctor(PhysicianDto physician);
	
	PhysicianProfileDto generatePhysicianProfile(long userRecordId, boolean excludeWorkProfile);
	
    List<EducationalQualificationDto> addEducationalQualification(List<EducationalQualificationDto> educationalQualificationDtos);
    
    PhysicianProfileDto addDoctorWorkHospital(List<PhysicianHospitalDto> physicianHospitalDtos);
    
    List<PhysicianProfileDto> findBySpecialityName(String specName);
    
    PhysicianDto findPhysicianById(String docId);

}


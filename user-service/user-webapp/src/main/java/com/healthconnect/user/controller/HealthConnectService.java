package com.healthconnect.user.controller;

import java.util.List;

import com.healthconnect.user.model.common.CommonDto;
import com.healthconnect.user.model.common.SimpleDto;
import com.healthconnect.user.model.common.SpecialityMaster;
import com.healthconnect.user.model.speciality.SpecialityDto;

public interface HealthConnectService {

    List<SimpleDto> findAllSpeciality();
    List<CommonDto> getBloodGroups();
    SpecialityDto getSpecialityByName(String name);
    
    SpecialityMaster getSpecialityMasterByName(String name);
    
	/*
	 * List<SimpleDto> findAllMedicalCouncils();
	 * 
	 * List<SimpleDto> findAllMedicalDegrees();
	 * 
	 * List<SimpleDto> findMedicalColleges(String college);
	 * 
	 * List<CommonDto> getAllRegistrationTypes();
	 */    
    
}


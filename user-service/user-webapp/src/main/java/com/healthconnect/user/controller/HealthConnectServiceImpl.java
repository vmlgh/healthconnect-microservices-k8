package com.healthconnect.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthconnect.user.common.ApiException;
import com.healthconnect.user.common.BaseService;
import com.healthconnect.user.model.common.CommonDto;
import com.healthconnect.user.model.common.SimpleDto;
import com.healthconnect.user.model.common.SpecialityMaster;
import com.healthconnect.user.model.enums.BloodGroupType;
import com.healthconnect.user.model.speciality.SpecialityDto;

@Service
@Transactional(readOnly = true)
public class HealthConnectServiceImpl extends BaseService implements HealthConnectService {

    private static final Logger logger = LoggerFactory.getLogger(HealthConnectServiceImpl.class);
    
    public static final String OTHER = "Other";
    public static final long OTHER_SPECIALITY_ID = -100;

    @Override
    public List<SimpleDto> findAllSpeciality() {
    	logger.info(authService.getAuthentication().getName());
    	logger.info("Fetch speciality list");
        List<SimpleDto> specialityDtos =  fetchSpeciality();
        specialityDtos.add(new SimpleDto(OTHER_SPECIALITY_ID, OTHER));
        return specialityDtos;
    }
    
    @Override
    public SpecialityDto getSpecialityByName(String name) {
    	logger.info(authService.getAuthentication().getName());
        return fetchSpecialityByName(name);
    }
    
    @Override
    public SpecialityMaster getSpecialityMasterByName(String name) {
    	SpecialityMaster speciality = specialityRepository.findByName(name);
    	
    	if(speciality == null) {
    		throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Invalid Speciality name");
    	}
    	
    	return speciality;
    }
    
    @Override
    public List<CommonDto> getBloodGroups(){
    	List<CommonDto> bloodGroups = new ArrayList<>();
    	for(BloodGroupType userType : BloodGroupType.values()) {
    		CommonDto dto = new CommonDto();
    		dto.setKey(userType.name());
    		dto.setValue(userType.getValue());
    		bloodGroups.add(dto);
    	}
    	return bloodGroups;
    }

	/*
	 * @Override public List<SimpleDto> findAllMedicalCouncils() {
	 * logger.info("Fetch medical councils."); List<SimpleDto> medicalCouncils =
	 * fetchMedicalCouncils(); medicalCouncils.add(new
	 * SimpleDto(OTHER_MEDICAL_COUNCIL_ID, OTHER)); return medicalCouncils; }
	 * 
	 * @Override public List<SimpleDto> findAllMedicalDegrees() {
	 * logger.info("Fetch all medical degrees."); List<SimpleDto> medicalDegrees =
	 * fetchMedicalDegrees(); medicalDegrees.add(new
	 * SimpleDto(OTHER_MEDICAL_DEGREE_ID, OTHER)); return medicalDegrees; }
	 * 
	 * @Override public List<SimpleDto> findMedicalColleges(String collegeName) {
	 * logger.info("Fetch college name " + collegeName); List<SimpleDto> colleges =
	 * fetchMedicalColleges(collegeName); colleges.add(new
	 * SimpleDto(OTHER_INSTITUTE_ID, OTHER)); return colleges; }
	 *
	 * 
	 * @Override public List<CommonDto> getAllRegistrationTypes() {
	 * logger.info("Fetch All registration Types "); return
	 * findAllRegistrationTypes(); }
	 */

    
}


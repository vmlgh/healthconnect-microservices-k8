package com.healthconnect.user.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.healthconnect.user.common.ApiException;
import com.healthconnect.user.model.common.PricingDto;
import com.healthconnect.user.model.common.SpecialityMaster;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.physician.EducationalQualification;
import com.healthconnect.user.model.physician.EducationalQualificationDto;
import com.healthconnect.user.model.physician.Hospital;
import com.healthconnect.user.model.physician.Physician;
import com.healthconnect.user.model.physician.PhysicianDto;
import com.healthconnect.user.model.physician.PhysicianHospitalDto;
import com.healthconnect.user.model.physician.PhysicianPricing;
import com.healthconnect.user.model.physician.PhysicianProfile;
import com.healthconnect.user.model.physician.PhysicianProfileDto;

@Service
@Transactional
public class PhysicianServiceImpl extends BasePhysicianService implements PhysicianService {

	@Override
	public PhysicianDto createDoctor(PhysicianDto physicianDto) {
        User user = (User) authService.getAuthentication().getDetails();
		Physician physician = physicianRepository.save(convertToPhysician(physicianDto, user));
		createOrUpdatePricing(physician ,physicianDto,user);
		return physicianDto;
	}
	
	private void createOrUpdatePricing(Physician physician, PhysicianDto physicianDto,User loggedInUser) {
		PhysicianPricing pricing;
		if (physicianDto.getPricing() != null) {
			PricingDto pricingDto = physicianDto.getPricing();
			pricing = pricingRespository.findPhysicianPricingByPhysicianRecordId(physician.getRecordId());
			if (pricing == null) {
				pricing = new PhysicianPricing();
				pricing.setPhysician(physician);
				pricing.setSpeciality(physician.getSpeciality());
				setDefaultOnCreate(pricing, loggedInUser);
			} else {
				setDefaultOnUpdate(pricing, loggedInUser);
			}
			pricing.setUnit(pricingDto.getUnit());
			pricing.setPrice(pricingDto.getPrice());
			pricingRespository.save(pricing);
		}

	}

	@Override
	public PhysicianProfileDto generatePhysicianProfile(long userId, boolean excludeWorkProfile) {
		PhysicianProfileDto response = new PhysicianProfileDto();
        Physician physician = physicianRepository.findPhysicianByUserRecordId(userId);
        if(physician == null) {
            return response;
        }
        PhysicianProfile dbProfile = physician.getPhysicianProfile();
        if(dbProfile != null || physician.getRegistrationNumber() != null) {
            response.setPersonalProfileExist(true);
            populatePersonalProfile(physician, dbProfile, response);
        }
        List<EducationalQualification> educationalQualifications = physician.getEducationalQualifications();
        if(!ObjectUtils.isEmpty(educationalQualifications)) {
            response.setEducationalProfileExist(true);
            populateProfessionalProfile(physician, educationalQualifications, response);
        }
        if(!excludeWorkProfile) {
            Set<Hospital> hospitals = physician.getHospitals();
            if (!ObjectUtils.isEmpty(hospitals)) {
                response.setWorkProfileExist(true);
                populateWorkProfile(physician, hospitals, response);
            }
        }
        return response;
	}
	
	@Override
    public List<EducationalQualificationDto> addEducationalQualification(List<EducationalQualificationDto> educationalQualificationDtos) {
	    //educationalQualificationDtos = filterAndDeleteEdQualifications(educationalQualificationDtos);
        User user = (User) authService.getAuthentication().getDetails();
        Physician dbPhysician = physicianRepository.findPhysicianByUserRecordId(user.getRecordId());
        if(dbPhysician == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Unable to add qualifications. Please create doctor profile first.");
        }
        List<EducationalQualification> educationalQualifications = educationalQualificationDtos.stream().map(educationalQualificationDto -> convertToEducationalQualification(educationalQualificationDto, user, dbPhysician)).collect(Collectors.toList());
        List<EducationalQualification> highestQualifications = educationalQualifications.stream().filter(educationalQualification -> educationalQualification.isHighestQual()).collect(Collectors.toList());
        if(highestQualifications.isEmpty()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Missing highest qualification.");
        }else if (highestQualifications.size() > 1){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Multiple highest qualification found.");
        }
        EducationalQualification duplicate = educationalQualifications.stream().filter(qualification -> Collections.frequency(educationalQualifications, qualification.getDegreeName()) > 1).findFirst().orElse(null);
        if(duplicate != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Multiple entries found for same degree name " + duplicate.getDegreeName());
        }
        educationalQualificationRepository.saveAll(educationalQualifications);
        dbPhysician.getPhysicianProfile().setHighestQualification(highestQualifications.get(0).getDegreeName());
        physicianRepository.save(dbPhysician);
        return educationalQualificationDtos;
    }
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public PhysicianProfileDto addDoctorWorkHospital(List<PhysicianHospitalDto> physicianHospitalDtos) {
        User user = (User) authService.getAuthentication().getDetails();
        Physician dbPhysician = physicianRepository.findPhysicianByUserRecordId(user.getRecordId());
        if(dbPhysician == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Unable to add doctor with hospital. Please create doctor profile first.");
        }
        //physicianHospitalDtos = filterAndDeleteHospitalPhysicianMapping(physicianHospitalDtos, dbPhysician);
        Set<Hospital> hospitals = new HashSet<>();
        for(PhysicianHospitalDto physicianHospitalDto : physicianHospitalDtos) {
            Hospital dbHospital;
			/*
			 * if(physicianHospitalDto.getHospitalId() > 0){ dbHospital =
			 * hospitalRepository.findById(physicianHospitalDto.getHospitalId()).get();
			 * }else {
			 */
                dbHospital = resolveDuplicacyAndSaveHospital(physicianHospitalDto, physicianHospitalDto.getAddress(), user);
            //}
            hospitals.add(dbHospital);
        }
        dbPhysician.setHospitals(hospitals);
        physicianRepository.save(dbPhysician);
        PhysicianProfileDto physicianProfileDto = generatePhysicianProfile(user.getRecordId(), true);
        populateWorkProfile(dbPhysician, hospitals, physicianProfileDto);
        return physicianProfileDto;
    }
	
	@Override
	public List<PhysicianProfileDto> findBySpecialityName(String specName) {
		List<PhysicianProfileDto> response = new ArrayList<>();
		if(!StringUtils.isEmpty(specName)) {
			SpecialityMaster specialityMaster = specialityRepository.findByName(specName);
			if(specialityMaster != null) {
				List<Physician> physicians = physicianRepository.findBySpecName(specName);
				
				for(Physician physician : physicians) {
					PhysicianProfileDto profileDto = new PhysicianProfileDto();
					PhysicianProfile dbProfile = physician.getPhysicianProfile();
			        if(dbProfile != null || physician.getRegistrationNumber() != null) {
			        	profileDto.setPersonalProfileExist(true);
			            populatePersonalProfile(physician, dbProfile, profileDto);
			        }
			        
					List<EducationalQualification> educationalQualifications = physician.getEducationalQualifications();
					if(!ObjectUtils.isEmpty(educationalQualifications)) {
						profileDto.setEducationalProfileExist(true);
						populateProfessionalProfile(physician, educationalQualifications, profileDto);
					}
					Set<Hospital> hospitals = physician.getHospitals();
					if (!ObjectUtils.isEmpty(hospitals)) {
						profileDto.setWorkProfileExist(true);
						populateWorkProfile(physician, hospitals, profileDto);
					}
					
					response.add(profileDto);
				}
				
			}
		}
		return response;
	}

	@Override
	public PhysicianDto findPhysicianById(String docId) {
		Physician doctor = physicianRepository.findPhysicianByUserId(docId);
		
        if(doctor == null) {
        	throw new ApiException(HttpStatus.BAD_REQUEST, "Doctor not found.");
        }
        PhysicianDto physicianDto  = convertToPhysicianDto(doctor);
		return physicianDto;
	}

	private PhysicianDto convertToPhysicianDto(Physician doctor) {
		PhysicianDto physicianDto = new PhysicianDto();
		physicianDto.setFirstName(doctor.getUser().getFirstName());
		physicianDto.setEmail(doctor.getUser().getEmail());
		physicianDto.setRecordId(doctor.getRecordId());
		physicianDto.setUserId(doctor.getUser().getUserId());
		return physicianDto;
	}

}

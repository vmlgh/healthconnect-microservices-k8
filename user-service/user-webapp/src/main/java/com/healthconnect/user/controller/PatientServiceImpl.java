package com.healthconnect.user.controller;

import java.util.Arrays;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.healthconnect.user.common.ApiException;
import com.healthconnect.user.model.common.AddressDto;
import com.healthconnect.user.model.common.CommonDto;
import com.healthconnect.user.model.common.PersonalProfile;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.enums.AppPageName;
import com.healthconnect.user.model.enums.UserStatus;
import com.healthconnect.user.model.patient.Patient;
import com.healthconnect.user.model.patient.PatientMasterDataDto;
import com.healthconnect.user.model.patient.PatientProfile;
import com.healthconnect.user.model.patient.PatientProfileDto;
import com.healthconnect.user.model.patient.PatientRegisterDto;
import com.healthconnect.user.model.patient.PatientRegisterResponseDto;
import com.healthconnect.user.model.util.HealthConnectUtility;
import com.healthconnect.user.repository.PatientRepository;

@Service
@Transactional
public class PatientServiceImpl extends BasePatientService implements PatientService {
	
	public static final String FIRST_NAME = "firstName";
  	public static final String LAST_NAME = "lastName";
	
	@Autowired
	protected PatientRepository patientRespository;
	
	@Override
	public PatientProfileDto getPatientProfile(long userId) {
		PatientProfileDto patientProfileDto  = new PatientProfileDto();
		Patient patient = patientRespository.findByUserRecordId(userId);
		if (patient != null) {
			User user = patient.getUser();
			PersonalProfile personalProfile = populatePersonalProfile(user);
			patientProfileDto.setPersonalProfile(personalProfile);
			patientProfileDto.setProfessionalProfile(getPatientProfessionalProfile(patient));
		}
		return patientProfileDto;
	}
	
	private PersonalProfile populatePersonalProfile(User user) {
		PersonalProfile personalProfile = new PersonalProfile();
		personalProfile.setEmail(user.getEmail());
		personalProfile.setEmailVerified(user.isEmailVerified());
		personalProfile.setFirstName(user.getFirstName());
		personalProfile.setLastName(user.getLastName());
		personalProfile.setUserId(user.getUserId());
		personalProfile.setUserType(Arrays.asList(user.getUserType()));
		personalProfile.setId(user.getRecordId());
		personalProfile.setMobileVerified(user.isMobileVerified());
		personalProfile.setUserStatus(user.getStatus().name());
		personalProfile.setProfileImageUrl(user.getProfileImageUrl());
		return personalProfile;
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public PatientRegisterResponseDto register(@Valid PatientRegisterDto patientDto) {
		PatientRegisterResponseDto response =  new PatientRegisterResponseDto();
		AddressDto addressDto = patientDto.getAddress();
		User loggedInUser = (User) authService.getAuthentication().getDetails();
		Patient patient = patientRespository.save(convertToPatientEntity(patientDto,addressDto,loggedInUser));
		Map<String, String> names = HealthConnectUtility.parseFirstNameAndLastName(patientDto.getName());
		loggedInUser.setFirstName(names.get(FIRST_NAME)); 
		if(!StringUtils.isEmpty(names.get(LAST_NAME)))
				loggedInUser.setLastName(names.get(LAST_NAME));
		loggedInUser.setStatus(UserStatus.PROFILE_COMPLETE);
		User user = userRepository.save(loggedInUser);
		PersonalProfile personalProfile = populatePersonalProfile(user);
		response.setPersonalProfile(personalProfile);
		response.setProfessionalProfile(getPatientProfessionalProfile(patient));
		//response.setProfileMasterData(getPatientMasterData(true));
		response.setLandingPage(AppPageName.PATIENT_DASHBOARD.name());
		return response;
	   
	}
	
	private Patient convertToPatientEntity(PatientRegisterDto patientDto, AddressDto addressDto, User loggedInUser) {
		Patient patient = new Patient();
		BeanUtils.copyProperties(patientDto, patient, "address");
		patient.setUser(loggedInUser);
		setDefaultOnCreate(patient, loggedInUser);
		patient.setAddress(resolveDuplicacyAndGetAddress(addressDto,loggedInUser));
		return patient;
	}
	
	private PatientProfile getPatientProfessionalProfile(Patient patient) {
		PatientProfile patientProfile = new PatientProfile();
		patientProfile.setName(patient.getName());
		if(patient.getBloodGroup() != null)
			patientProfile.setBloodGroup(patient.getBloodGroup().getValue());
		patientProfile.setAddress(patient.getAddress().toAddressDto());
		patientProfile.setGender(patient.getGender());
		patientProfile.setPatientId(patient.getRecordId());
		patientProfile.setAge(patient.getAge());
		patientProfile.setWeight(patient.getWeight());
		return patientProfile;
	}

	@Override
	public PatientMasterDataDto getPatientMasterData() {
		PatientMasterDataDto  masterDto = new PatientMasterDataDto();
		masterDto.setBloodGroups(getBloodGroups());

		masterDto.setGenders(getGenders());

		masterDto.setHealthConnectServices(fetchAllMedicoServices());
		return masterDto;
	}

	@Override
	public CommonDto updateProfileImage(String imageUrl, long patientId) {
		validatePatientId(patientId);
		User user = (User) authService.getAuthentication().getDetails();
		user = userRepository.findById(user.getRecordId()).get();
		user.setProfileImageUrl(imageUrl);
		return new CommonDto("url", imageUrl);
	}
	
	@Override
	public PatientRegisterResponseDto updatePatientProfile(long patientId,PatientRegisterDto patientDto) {
		User loggedInUser = (User) authService.getAuthentication().getDetails();
		Patient patientObj = patientRespository.findPatientByUser(loggedInUser);
		if(patientObj == null || patientObj.getUser().getRecordId() != patientId) {
	            throw new ApiException(HttpStatus.BAD_REQUEST, "Patient not found.");
	    }
		
		/*
		 * AddressDto addressDto = patientRegisterDto.getAddress(); User loggedInUser =
		 * (User) authService.getAuthentication().getDetails(); patient =
		 * convertToPatientEntity(patientRegisterDto,addressDto,loggedInUser);
		 * 
		 * //patientRespository.updatePatient(patientRegisterDto.getName(),
		 * patientRegisterDto.getAge(), patientRegisterDto.getWeight(),
		 * patientRegisterDto.getBloodGroup().name(),
		 * patientRegisterDto.getGender().name(), patientObj.getAddress().getRecordId(),
		 * patient.getRecordId()); patientRespository.save(patient);
		 */
		
		PatientRegisterResponseDto response =  new PatientRegisterResponseDto();
		AddressDto addressDto = patientDto.getAddress();
		patientObj = convertToPatientEntity(patientDto,addressDto,loggedInUser);
		Patient patient = patientRespository.save(patientObj);
		Map<String, String> names = HealthConnectUtility.parseFirstNameAndLastName(patientDto.getName());
		loggedInUser.setFirstName(names.get(FIRST_NAME)); 
		if(!StringUtils.isEmpty(names.get(LAST_NAME)))
				loggedInUser.setLastName(names.get(LAST_NAME));
		loggedInUser.setStatus(UserStatus.PROFILE_COMPLETE);
		User user = userRepository.save(loggedInUser);
		PersonalProfile personalProfile = populatePersonalProfile(user);
		response.setPersonalProfile(personalProfile);
		response.setProfessionalProfile(getPatientProfessionalProfile(patient));
		//response.setProfileMasterData(getPatientMasterData(true));
		response.setLandingPage(AppPageName.PATIENT_DASHBOARD.name());
		return response;
	}

}

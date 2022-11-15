package com.healthconnect.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import com.healthconnect.user.common.ApiException;
import com.healthconnect.user.model.common.BaseAccessDto;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.enums.AppPageName;
import com.healthconnect.user.model.enums.UserStatus;
import com.healthconnect.user.model.enums.UserType;
import com.healthconnect.user.model.patient.PatientProfileDto;
import com.healthconnect.user.model.physician.PhysicianProfileDto;
import com.healthconnect.user.model.user.AppLoginResponse;
import com.healthconnect.user.model.user.UserSignUpResponse;
import com.healthconnect.user.model.util.CryptoUtility;
import com.healthconnect.user.model.util.HealthConnectUtility;
import com.healthconnect.user.repository.UserRepository;
import com.healthconnect.user.security.JwtTokenUtil;

public abstract class BaseUserService {
	
	@Autowired
    protected UserRepository userRepository;
	
	@Autowired
    protected Environment environment; //what is this
	
	@Autowired
	protected JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private PatientService patienService;
	
	@Autowired
    private PhysicianService physicianService;

	protected User getUserByEmailOrMobileNumber(BaseAccessDto loginDto , boolean isEmail) {
    	if(isEmail)
    		return findUserByEmailAndService(loginDto);
    	return findUserByMobileAndService(loginDto);
    }
	
	protected  User findUserByEmailAndService(BaseAccessDto loginDto) {
        return userRepository.findByEmailAndUserType(loginDto.getEmail(), loginDto.getUserType());
    }
	
	protected User findUserByMobileAndService(BaseAccessDto loginDto) {
        return userRepository.findByMobileNumberAndUserType(loginDto.getEmail(), loginDto.getUserType());
    }
	
	protected void validateIfSignUpAllowed(User user) {
        if(user == null) {
            return;
        }
        if(user.isDeleted() || user.getStatus() == UserStatus.BLOCKED) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "User is disabled. Please contact admin.");
        }else {
            throw new ApiException(HttpStatus.BAD_REQUEST, String.format("User already exist for service type '%s'. Try login instead.", user.getUserType().getValue().toUpperCase()));
        }
    }
	
	protected UserSignUpResponse generateSignUpResponse(User user, boolean isEmail) {
        UserSignUpResponse userSignupResponse = new UserSignUpResponse();
        userSignupResponse.setEmailVerified(false);
        userSignupResponse.setMobileVerified(false);
        userSignupResponse.setId(user.getRecordId());
        userSignupResponse.setUserType(user.getUserType());
        userSignupResponse.setEmail(user.getEmail());
        userSignupResponse.setPhone(user.getMobileNumber());
        userSignupResponse.setUsername(user.getUsername());
        userSignupResponse.setUserStatus(UserStatus.PROFILE_INCOMPLETE.name());
        userSignupResponse.setUserId(user.getUserId());
        userSignupResponse.setToken(jwtTokenUtil.generateToken(user));
        userSignupResponse.setLandingPage(identifyUserSingUpLandingPage(user));
        user.setToken(userSignupResponse.getToken());
        return userSignupResponse;
    }

    private String identifyUserSingUpLandingPage(User user) {
    	String signUplandingPage = null;
    	if(user.getUserType().equals(UserType.DOCTOR)) {
    		signUplandingPage = AppPageName.DOCTOR_PERSONAL_INFO.name();
    	}else if(user.getUserType().equals(UserType.PATIENT)) {
    		signUplandingPage = AppPageName.PATIENT_REG_INFO.name();
    	}
    	return signUplandingPage;
	}
    
    protected AppLoginResponse generateUserLoginResponse(User user){
        AppLoginResponse response = new AppLoginResponse();
        switch(user.getUserType()) {
            case DOCTOR:
                response = getDoctorProfile(user);
                break;
            case PATIENT:
            	response = getPatientProfile(user);
            	break;
        }
        return response;
    }
    
    private AppLoginResponse getPatientProfile(User user) {
		AppLoginResponse response = new AppLoginResponse();
		long userId = HealthConnectUtility.extractRecordIdFromUserId(user.getUserId());
		PatientProfileDto patientProfileDto = patienService.getPatientProfile(userId);
		if(patientProfileDto.getPersonalProfile() == null) {
			response.setLandingPage(AppPageName.PATIENT_REG_INFO.name());
		} else{
			response.setLandingPage(AppPageName.PATIENT_DASHBOARD.name());
			response.setPersonalProfile(patientProfileDto.getPersonalProfile());
		}
		response.setToken(jwtTokenUtil.generateToken(user));
		return response;
	}
    
    protected String getEmailVerificationLink(String userId) {
        return environment.getProperty("healthconnect.base.url") + "user/verify-email?id=" + CryptoUtility.aesEncrypt(userId);
    }
    
    private AppLoginResponse getDoctorProfile(User user) {
        AppLoginResponse response = new AppLoginResponse();
        long userId = HealthConnectUtility.extractRecordIdFromUserId(user.getUserId());
        PhysicianProfileDto physicianProfile = physicianService.generatePhysicianProfile(userId, false);
        response.setToken(jwtTokenUtil.generateToken(user));
        response.setPersonalProfile(physicianProfile.getPersonalProfile());
        //response.setProfessionalProfile(physicianProfile.getPhysicianProfessionalProfile());
        response.setLandingPage(identifyDoctorLandingPage(physicianProfile));
        return response;
    }
    
    private String identifyDoctorLandingPage(PhysicianProfileDto physicianProfile){
        String landingPage;
        if(!physicianProfile.isPersonalProfileExist()){
            landingPage = AppPageName.DOCTOR_PERSONAL_INFO.name();
        }else if(!physicianProfile.isEducationalProfileExist()){
            landingPage = AppPageName.DOCTOR_EDUCATIONAL_INFO.name();
        }else if(!physicianProfile.isWorkProfileExist()){
            landingPage = AppPageName.DOCTOR_WORK_INFO.name();
        }else {
            landingPage = AppPageName.DOCTOR_WORK_INFO.name();
            //landingPage = AppPageName.DOCTOR_DASHBOARD.name();
        }
        return landingPage;
    }

}

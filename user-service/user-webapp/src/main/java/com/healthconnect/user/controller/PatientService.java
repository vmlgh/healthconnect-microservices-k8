package com.healthconnect.user.controller;

import javax.validation.Valid;

import com.healthconnect.user.model.common.BaseMasterDataDto;
import com.healthconnect.user.model.common.CommonDto;
import com.healthconnect.user.model.patient.PatientProfileDto;
import com.healthconnect.user.model.patient.PatientRegisterDto;
import com.healthconnect.user.model.patient.PatientRegisterResponseDto;

public interface PatientService {

	PatientProfileDto getPatientProfile(long userId);
	
	PatientRegisterResponseDto register(@Valid PatientRegisterDto patientDto);
	
	BaseMasterDataDto getPatientMasterData();
	
	CommonDto updateProfileImage(String imageUrl, long patientId);
	
	PatientRegisterResponseDto updatePatientProfile(long patientId,PatientRegisterDto patientRegisterDto);
}


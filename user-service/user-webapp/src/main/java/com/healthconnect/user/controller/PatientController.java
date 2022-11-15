package com.healthconnect.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthconnect.user.model.common.CommonDto;
import com.healthconnect.user.model.patient.PatientProfileDto;
import com.healthconnect.user.model.patient.PatientRegisterDto;
import com.healthconnect.user.model.patient.PatientRegisterResponseDto;
import com.healthconnect.user.model.util.ApiResponse;
import com.healthconnect.user.model.util.HealthConnectUtility;


//@Api(value = "Patient APIs", description = "Operations pertaining to patient")
@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@Secured({ "ROLE_PATIENT" })
	@PostMapping("/register")
	public ApiResponse<PatientRegisterResponseDto> register(@RequestBody @Valid PatientRegisterDto patientDto) {
		return new ApiResponse<>(HttpStatus.OK, "success", patientService.register(patientDto));
	}

	@Secured({ "ROLE_PATIENT"})
	@PutMapping("/{patientId}/profile-image")
	public ApiResponse<CommonDto> createMedicalRecord(@RequestParam String imageUrl, @PathVariable String patientId) {
		long userId = HealthConnectUtility.extractRecordIdFromUserId(patientId);
		return new ApiResponse<>(HttpStatus.OK, "success", patientService.updateProfileImage(imageUrl, userId));
	}
	
	@Secured({ "ROLE_PATIENT" })
	@GetMapping("/{patientId}")
	public ApiResponse<PatientProfileDto> getPatientProfile(@PathVariable String patientId) {
		long userId = HealthConnectUtility.extractRecordIdFromUserId(patientId);
		return new ApiResponse<>(HttpStatus.OK, "success", patientService.getPatientProfile(userId));
	}	

	@Secured({"ROLE_PATIENT"})
	@PutMapping("/{patientId}")
	public ApiResponse<PatientRegisterResponseDto> updatePatientProfile(@PathVariable String patientId,@RequestBody @Valid PatientRegisterDto patientRegisterDto) {
		long userId = HealthConnectUtility.extractRecordIdFromUserId(patientId);
		return new ApiResponse<>(HttpStatus.OK, "success", patientService.updatePatientProfile(userId,patientRegisterDto));
	}
}

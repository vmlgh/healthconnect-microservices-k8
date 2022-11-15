package com.healthconnect.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.healthconnect.user.common.ApiException;
import com.healthconnect.user.common.BaseService;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.patient.Patient;
import com.healthconnect.user.repository.PatientRepository;

public abstract class BasePatientService extends BaseService<Patient> {
	
	@Autowired
	protected PatientRepository patientRespository;
	
	protected Patient validatePatientId(long patientId) {
        User user = (User) authService.getAuthentication().getDetails();
        Patient patient = patientRespository.findByUserRecordId(user.getRecordId());
        if(patient == null || patient.getUser().getRecordId() != patientId) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Patient not found.");
        }
        return patient;
    }

}

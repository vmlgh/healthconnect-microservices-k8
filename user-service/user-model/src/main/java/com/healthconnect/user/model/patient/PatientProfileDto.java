package com.healthconnect.user.model.patient;

import com.healthconnect.user.model.common.PersonalProfile;

public class PatientProfileDto {

	private PersonalProfile personalProfile;
	
	private PatientProfile professionalProfile;

	public PersonalProfile getPersonalProfile() {
		return personalProfile;
	}

	public void setPersonalProfile(PersonalProfile personalProfile) {
		this.personalProfile = personalProfile;
	}

	public PatientProfile getProfessionalProfile() {
		return professionalProfile;
	}

	public void setProfessionalProfile(PatientProfile professionalProfile) {
		this.professionalProfile = professionalProfile;
	}

	
}


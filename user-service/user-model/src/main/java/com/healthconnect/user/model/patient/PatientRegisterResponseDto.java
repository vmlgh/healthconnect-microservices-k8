package com.healthconnect.user.model.patient;

import com.healthconnect.user.model.common.PersonalProfile;

public class PatientRegisterResponseDto {
	
	private PatientProfile professionalProfile;
	
	private PersonalProfile personalProfile;
	
	//private PatientMasterDataDto profileMasterData;
	
	private String landingPage;
	
	public PersonalProfile getPersonalProfile() {
		return personalProfile;
	}

	public void setPersonalProfile(PersonalProfile personalProfile) {
		this.personalProfile = personalProfile;
	}

	/*
	 * public PatientMasterDataDto getProfileMasterData() { return
	 * profileMasterData; }
	 * 
	 * public void setProfileMasterData(PatientMasterDataDto profileMasterData) {
	 * this.profileMasterData = profileMasterData; }
	 */

	public String getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	public PatientProfile getProfessionalProfile() {
		return professionalProfile;
	}

	public void setProfessionalProfile(PatientProfile professionalProfile) {
		this.professionalProfile = professionalProfile;
	}


	

}


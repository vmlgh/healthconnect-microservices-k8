package com.healthconnect.user.model.physician;

import java.util.List;

import com.healthconnect.user.model.common.NotificationDto;
import com.healthconnect.user.model.common.PricingDto;

public class PhysicianProfessionalProfile {

    private int yearsOfExp;
    private String profileDesc;
    private String regdCouncil;
    private String registrationNumber;
    private String regdYear;
    private Double rating;
    private String fellowships;
    private String speciality;
    private List<NotificationDto> notifications;
    private List<EducationalQualificationDto> educationalQualifications;
    private List<PhysicianHospitalDto> hospitals;
    private PricingDto pricing;

    public List<EducationalQualificationDto> getEducationalQualifications() {
        return educationalQualifications;
    }

    public void setEducationalQualifications(List<EducationalQualificationDto> educationalQualifications) {
        this.educationalQualifications = educationalQualifications;
    }

    public int getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(int yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    public String getRegdCouncil() {
        return regdCouncil;
    }

    public void setRegdCouncil(String regdCouncil) {
        this.regdCouncil = regdCouncil;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegdYear() {
        return regdYear;
    }

    public void setRegdYear(String regdYear) {
        this.regdYear = regdYear;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getFellowships() {
        return fellowships;
    }

    public void setFellowships(String fellowships) {
        this.fellowships = fellowships;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<NotificationDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationDto> notifications) {
        this.notifications = notifications;
    }

    public List<PhysicianHospitalDto> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<PhysicianHospitalDto> hospitals) {
        this.hospitals = hospitals;
    }
    
	public PricingDto getPricing() {
		return pricing;
	}

	public void setPricing(PricingDto pricing) {
		this.pricing = pricing;
	}
}

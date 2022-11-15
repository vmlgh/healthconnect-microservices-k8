package com.healthconnect.user.model.physician;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.healthconnect.user.model.common.MedicalCouncilMaster;
import com.healthconnect.user.model.core.BaseEntity;

/**
 * The Class PhysicianDto.
 */
@Entity
@Table(name = "PhysicianProfile")
public class PhysicianProfile extends BaseEntity {

	@Column(name = "YearsOfExp", columnDefinition = "int default 0")
	private int yrsOfExp;

	@Column(name = "ProfileTitle")
	private String profileTitle;

	@Column(name = "ProfileDesc", length = 4000)
	private String profileDesc;

	@Column(name = "HighestQualification")
	private String highestQualification;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RegdCouncil", referencedColumnName = "name", nullable = false)
	private MedicalCouncilMaster regCouncil;

	@Column(name = "RegdYear")
	private String regYear;

	@Column(name = "AverageRating")
	private Double averageRatings;

	public String getRegYear() {
		return regYear;
	}

	public void setRegYear(String regYear) {
		this.regYear = regYear;
	}

	public int getYrsOfExp() {
		return yrsOfExp;
	}

	public void setYrsOfExp(int yrsOfExp) {
		this.yrsOfExp = yrsOfExp;
	}

	public String getProfileDesc() {
		return profileDesc;
	}

	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public MedicalCouncilMaster getRegCouncil() {
		return regCouncil;
	}

	public void setRegCouncil(MedicalCouncilMaster regCouncil) {
		this.regCouncil = regCouncil;
	}

	public Double getAverageRatings() {
		return averageRatings;
	}

	public void setAverageRatings(Double averageRatings) {
		this.averageRatings = averageRatings;
	}

	public String getProfileTitle() {
		return profileTitle;
	}

	public void setProfileTitle(String profileTitle) {
		this.profileTitle = profileTitle;
	}
}


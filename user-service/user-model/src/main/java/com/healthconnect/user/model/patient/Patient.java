package com.healthconnect.user.model.patient;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.healthconnect.user.model.common.Address;
import com.healthconnect.user.model.core.BaseEntity;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.enums.BloodGroupType;
import com.healthconnect.user.model.enums.GenderType;

@Entity
@Table(name = "Patient")
public class Patient extends BaseEntity {

	@Column(name = "Name")
	private String name;

	@Column(name = "Age")
	private int age;

	@Column(name = "Gender")
	@Enumerated(EnumType.STRING)
	private GenderType gender;

	@Column(name = "Weight")
	private double weight;

	@Column(name = "BloodGroup")
	@Enumerated(EnumType.STRING)
	private BloodGroupType bloodGroup;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AddressId")
	private Address address;

	@OneToOne
	@JoinColumn(name = "UserId", referencedColumnName = "userId")
	private User user;

	/*
	 * @OneToOne
	 * 
	 * @JoinColumn(name = "HealthProfileId") private HealthProfile healthProfile;
	 * 
	 * @OneToMany(mappedBy = "patient") private List<PatientQuery> queries;
	 */

	@OneToMany(mappedBy = "patient")
	private List<PatientMedicalRecord> medicalRecords;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BloodGroupType getBloodGroup() {
		return bloodGroup;
	}

	/*
	 * public HealthProfile getHealthProfile() { return healthProfile; }
	 * 
	 * public void setHealthProfile(HealthProfile healthProfile) {
	 * this.healthProfile = healthProfile; }
	 * 
	 * public List<PatientQuery> getQueries() { return queries; }
	 * 
	 * public void setQueries(List<PatientQuery> queries) { this.queries = queries;
	 * }
	 */

	public void setBloodGroup(BloodGroupType bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public List<PatientMedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(List<PatientMedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
}


package com.healthconnect.user.model.patient;

import javax.validation.constraints.NotEmpty;

import com.healthconnect.user.model.common.AddressDto;
import com.healthconnect.user.model.enums.BloodGroupType;
import com.healthconnect.user.model.enums.GenderType;

public class PatientRegisterDto {

	/**
	 * Serial VersionUID
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "{patient.name.empty.error}")
	private String name;

	private int age;
	
	private GenderType gender;
	
	private double weight;
	
	private BloodGroupType bloodGroup;
	
	private AddressDto address;

	public BloodGroupType getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroupType bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}


}

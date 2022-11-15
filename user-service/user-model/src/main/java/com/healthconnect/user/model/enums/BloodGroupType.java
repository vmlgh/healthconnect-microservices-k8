package com.healthconnect.user.model.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BloodGroupType {
	O_POSITIVE("O+ve"), A_POSITIVE("A+ve"), B_POSITIVE("B+ve"), AB_POSTIVE("AB+ve"), O_NEGATIVE("O-ve"), A_NEGATIVE(
			"A-"), B_NEGATIVE("B-ve"), AB_NEGATIVE("AB-ve");

	private String value;

	BloodGroupType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static BloodGroupType fromValue(String value) {
		for (BloodGroupType BloodGroupType : values()) {
			if (BloodGroupType.value.equalsIgnoreCase(value)) {
				return BloodGroupType;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	public static BloodGroupType create(String value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		for (BloodGroupType v : values()) {
			if (value.equals(v.value)) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public String toString() {
		return value;
	}

	public boolean isUniversalDonor() {
		return false;
		// TODO
	}

	public boolean isUniversalRecepient() {
		return false;
		// TODO
	}

}


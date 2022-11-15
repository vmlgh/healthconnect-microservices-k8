package com.healthconnect.user.model.physician;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class BasePhysicianDto {

	private String name;

	@NotEmpty(message = "{physician.regdno.empty.error}")
	private String regdNo;

	private long specialityId;

	private String specialityName;
	
	private String phone;
	
	private long recordId;
	
	@Pattern(regexp ="[A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+",message="{email.invalid.error}")
	private String email;
	
	//private boolean deleted;
	
	//private String dialingCode;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegdNo() {
		return regdNo;
	}

	public void setRegdNo(String regdNo) {
		this.regdNo = regdNo;
	}

	public long getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(long specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/*
	 * public boolean isDeleted() { return deleted; }
	 * 
	 * public void setDeleted(boolean deleted) { this.deleted = deleted; }
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	/*
	 * public String getDialingCode() { return dialingCode; }
	 * 
	 * public void setDialingCode(String dialingCode) { this.dialingCode =
	 * dialingCode; }
	 */
	
}

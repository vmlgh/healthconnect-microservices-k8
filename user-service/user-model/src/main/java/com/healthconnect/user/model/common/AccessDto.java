package com.healthconnect.user.model.common;

import javax.validation.constraints.NotEmpty;

public class AccessDto extends BaseAccessDto {

	@NotEmpty(message = "{password.empty.error}")
	private String password;

	//private String otp;

	public String getPassword() {
		return password;
	}

	/*
	 * public String getOtp() { return otp; }
	 * 
	 * public void setOtp(String otp) { this.otp = otp; }
	 */
}


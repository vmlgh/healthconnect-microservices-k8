package com.healthconnect.user.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;
import com.healthconnect.user.model.enums.OtpType;

@Entity
@Table(name ="HEALTHCONNECT_OTP")
public class HealthConnectOtp extends BaseEntity {

    @Column(name = "EmailPhone")
    private String emailPhone;
    @Column(name = "OTP")
    private String otp;
    @Column(name = "Expiry")
    private long expiry;
    @Column(name = "Type")
    @Enumerated(value=EnumType.STRING)
    private OtpType type;
    
    @Column(name = "Attempt")
    private int attempt;
    
    public HealthConnectOtp() {

    }

    public HealthConnectOtp(String identifier, String otp){
        this.emailPhone =identifier;
        this.otp = otp;
        this.expiry = System.currentTimeMillis() + 10*60*1000;
        this.attempt = 0;
    }

    public HealthConnectOtp(String identifier, String otp, OtpType otpType){
        this.emailPhone =identifier;
        this.otp = otp;
        this.expiry = System.currentTimeMillis() + 10*60*1000;
        this.attempt = 0;
        this.type = otpType;
    }
    
    public String getEmailPhone() {
        return emailPhone;
    }

    public void setEmailPhone(String emailPhone) {
        this.emailPhone = emailPhone;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public long getExpiry() {
        return expiry;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

	public OtpType getType() {
		return type;
	}

	public void setType(OtpType type) {
		this.type = type;
	}
}


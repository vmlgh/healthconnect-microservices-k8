package com.healthconnect.user.model.enums;

public enum OutboxJobType {

    WELCOME(true, true, "welcome"),
    MOBILE_VERIFICATION_OTP(true, false, true, "mobile.verification.otp.template", null),
    HOSPITAL_PHYSICIAN_WELCOME_SMS(true, false,true,"mobile.hospital.physician.welcome.template",null),
    HOSPITAL_PHYSICIAN_WELCOME_EMAIL(true ,true,false, null,"hospital_physican_welcome" ),
    EMAIL_OTP(true, true, "otp"),
	MEDICO_FORGOT_PASSWORD_EMAIL_OTP(true,true,"forgot_password"),
	MEDICO_FORGOT_PASSWORD_MOBILE_OTP(true,false,true,"medico.forgot.papssword.otp.template",null),
	APPOINTMENT(true, true, "appointment");

    private boolean immediate;
    private boolean sendSms;
    private boolean sendEmail;
    private String smsTemplateId;
    private String emailTemplateId;

    OutboxJobType(boolean immediate, boolean sendEmailRequired, boolean sendSmsRequired, String smsTemplateId, String emailTemplateId) {
        this.immediate = immediate;
        this.sendEmail = sendEmailRequired;
        this.sendSms = sendSmsRequired;
        this.smsTemplateId = smsTemplateId;
        this.emailTemplateId = emailTemplateId;
    }

    OutboxJobType(boolean immediate, boolean sendEmailRequired, String emailTemplateId) {
        this.immediate = immediate;
        this.sendEmail = sendEmailRequired;
        this.emailTemplateId = emailTemplateId;
    }

    public boolean isImmediate() {
        return immediate;
    }

    public void setImmediate(boolean immediate) {
        this.immediate = immediate;
    }

    public boolean isSendSms() {
        return sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    public String getEmailTemplateId() {
        return emailTemplateId;
    }

    public void setEmailTemplateId(String emailTemplateId) {
        this.emailTemplateId = emailTemplateId;
    }
}

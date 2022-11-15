package com.healthconnect.user.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;
import com.healthconnect.user.model.enums.JobType;

@Entity
@Table(name = "HealthConnectJob")
public class HealthConnectJob extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "JobType")
    private JobType jobType;

    @Column(name = "JobName")
    private String jobName;

    @Column(name = "Attempt")
    private long attempt;

    @Column(name = "SendSms")
    private boolean sendSms;

    @Column(name = "SmsData")
    private String smsData;

    @Column(name = "SmsPending")
    private boolean smsPending;

    @Column(name = "emailPending")
    private boolean emailPending;

    @Column(name = "SendEmail")
    private boolean sendEmail;

    @Column(name = "Content")
    private String content;

    @Column(name = "ExtraData")
    private String extraData;

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public long getAttempt() {
        return attempt;
    }

    public void setAttempt(long attempt) {
        this.attempt = attempt;
    }

    public boolean isSendSms() {
        return sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }

    public String getSmsData() {
        return smsData;
    }

    public void setSmsData(String smsData) {
        this.smsData = smsData;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public boolean hasMaxAttemptsReached() {
        return attempt >= 5;
    }

    public boolean isSmsPending() {
        return smsPending;
    }

    public void setSmsPending(boolean smsPending) {
        this.smsPending = smsPending;
    }

    public boolean isEmailPending() {
        return emailPending;
    }

    public void setEmailPending(boolean emailPending) {
        this.emailPending = emailPending;
    }
}


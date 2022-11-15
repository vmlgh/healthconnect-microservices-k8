package com.healthconnect.appointment.model;

import javax.persistence.Column;

import com.healthconnect.user.model.common.Slot;

public class AppointmentDto {
	
	String patientId;
		
	String doctorId;
	
	String speciality;
	
	String paymentStripe;
	
	String videoChannel;
	
	String doctorName;

	@Column(name ="appointmentDate")
	private String appointmentDate;
		
	private Slot slot;
	
	private double payment;
	
	long recordId;
	
	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getPaymentStripe() {
		return paymentStripe;
	}

	public void setPaymentStripe(String paymentStripe) {
		this.paymentStripe = paymentStripe;
	}

	public String getVideoChannel() {
		return videoChannel;
	}

	public void setVideoChannel(String videoChannel) {
		this.videoChannel = videoChannel;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
}

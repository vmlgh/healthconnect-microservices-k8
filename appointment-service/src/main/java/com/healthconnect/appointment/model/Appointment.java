package com.healthconnect.appointment.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;

@Entity
@Table(name = "Appointment")
public class Appointment extends BaseEntity {
	
	String patientId;
	
	String doctorId;
	
	String speciality;
	
	String paymentStripe;
	
	String videoChannel;
	
	Double payment;
	
	String doctorName;

	@Column(name ="appointmentDate")
	private LocalDate appointmentDate;
		
	@Column(name = "Slot", length = 400)
    private String slot;
	
	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}
	
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

}

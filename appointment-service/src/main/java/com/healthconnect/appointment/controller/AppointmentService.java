package com.healthconnect.appointment.controller;

import java.util.List;

import javax.validation.Valid;

import com.healthconnect.appointment.model.AppointmentDto;

public interface AppointmentService {
	
	AppointmentDto createAppointment(@Valid AppointmentDto appointmentDto);
	
	AppointmentDto addPaymentAndVideoChannel(String paymentUrl, String videoChannel, long recordId);
	
	List<AppointmentDto> getAppointments();

}

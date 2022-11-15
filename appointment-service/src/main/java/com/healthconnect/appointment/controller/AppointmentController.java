package com.healthconnect.appointment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthconnect.appointment.model.AppointmentDto;
import com.healthconnect.user.model.util.ApiResponse;

//@Api(value = "Patient APIs", description = "Operations pertaining to appointment")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Secured({ "ROLE_PATIENT" })
	@PostMapping
	public ApiResponse<AppointmentDto> addAppointment(@RequestBody @Valid AppointmentDto appointmentDto) {
		return new ApiResponse<>(HttpStatus.OK, "success", appointmentService.createAppointment(appointmentDto));
	}
	
	@Secured({ "ROLE_PATIENT" })
	@PutMapping("/{appointmentId}/updateInfo")
	public ApiResponse<AppointmentDto> updatePaymentAndVideoLink(@RequestParam String paymentLink, @RequestParam String videoChannel, @PathVariable long appointmentId) {
		return new ApiResponse<>(HttpStatus.NO_CONTENT, "success", appointmentService.addPaymentAndVideoChannel(paymentLink, videoChannel, appointmentId));
	}
	
	@Secured({ "ROLE_PATIENT", "ROLE_DOCTOR" })
	@GetMapping
	public ApiResponse<List<AppointmentDto>> getUserAppointments() {
		return new ApiResponse<>(HttpStatus.OK, "success", appointmentService.getAppointments());
	}

}

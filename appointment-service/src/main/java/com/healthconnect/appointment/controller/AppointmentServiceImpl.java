package com.healthconnect.appointment.controller;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.healthconnect.appointment.client.AppointmentServiceClientImpl;
import com.healthconnect.appointment.model.ApiException;
import com.healthconnect.appointment.model.Appointment;
import com.healthconnect.appointment.model.AppointmentDto;
import com.healthconnect.appointment.repository.AppointmentRepository;
import com.healthconnect.appointment.repository.PhysicianPricingRepository;
import com.healthconnect.appointment.security.AuthenticationFacadeService;
import com.healthconnect.user.model.common.Slot;
import com.healthconnect.user.model.common.SpecialityMaster;
import com.healthconnect.user.model.core.BaseEntity;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.enums.UserType;
import com.healthconnect.user.model.physician.Physician;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
    protected AuthenticationFacadeService authService;
	
	@Autowired
    protected AppointmentRepository appointmentRepository;
	
	@Autowired
	protected PhysicianPricingRepository pricingRespository;
	
	@Autowired
    private AppointmentServiceClientImpl appointmentServiceClient;
	
	/*
	 * @Autowired protected HealthConnectJobService jobService;
	 */

	@Override
	public AppointmentDto createAppointment(@Valid AppointmentDto appointmentDto) {
		
		User patient = (User) authService.getAuthentication().getDetails();
		
		Appointment dbAppointment = appointmentRepository.findAppointment(patient.getUserId(), 
				LocalDate.parse(appointmentDto.getAppointmentDate()), new Gson().toJson(appointmentDto.getSlot()));
		if(dbAppointment != null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "An appointment already exists at this time and slot");
		}
		
		Appointment appointment = new Appointment();
		
		appointment.setPatientId(patient.getUserId());		
		String doctorId = appointmentDto.getDoctorId();
		
		Physician physician = appointmentServiceClient.findPhysicianById(doctorId);
		if(physician == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid doctor Id");
		}
		
		
		Double pricing = pricingRespository.findPhysicianPricingByPhysicianRecordId(physician.getRecordId()).getPrice();		
		appointmentDto.setPayment(pricing != 0L ? pricing : 150);
		appointment.setPayment(pricing != 0L ? pricing : 150);
		
		String speciality = appointmentDto.getSpeciality();
		SpecialityMaster specialityMaster = appointmentServiceClient.findSpecialityByName(speciality);
		if(specialityMaster == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid speciality");
		}		
		appointment.setSpeciality(appointmentDto.getSpeciality());
		appointment.setDoctorId(appointmentDto.getDoctorId());
		appointment.setPaymentStripe(appointmentDto.getPaymentStripe());
		appointment.setVideoChannel(appointmentDto.getVideoChannel());		
		appointment.setAppointmentDate(LocalDate.parse(appointmentDto.getAppointmentDate()));
		appointment.setSlot(new Gson().toJson(appointmentDto.getSlot()));
		appointment.setDoctorName("Dr." + physician.getUser().getFirstName());
		setDefaultOnCreate(appointment, patient);
		
		appointment = appointmentRepository.save(appointment);
		
		appointmentDto.setRecordId(appointment.getRecordId());
		appointmentDto.setPatientId(appointment.getPatientId());		
		appointmentDto.setDoctorName("Dr." + physician.getUser().getFirstName());
		
		return appointmentDto;
	}

	@Override
	public AppointmentDto addPaymentAndVideoChannel(String paymentUrl, String videoChannel, long recordId) {
		
		User patient = (User) authService.getAuthentication().getDetails();
		
		if(StringUtils.isEmpty(paymentUrl) || StringUtils.isEmpty(videoChannel)) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "payment url and video channel cannot be empty");
		}
		
		Optional<Appointment> appointment = appointmentRepository.findById(recordId);
		if(appointment.isEmpty()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid appointment Id");
		}
		
		appointmentRepository.updateAppointment(paymentUrl, videoChannel, recordId);
		
		Appointment appointmentUpdated = appointmentRepository.getById(recordId);
		
		AppointmentDto appDto = new AppointmentDto();
				
	    convertToAppointmentDto(appDto, appointmentUpdated);
	    
	    Physician physician = appointmentServiceClient.findPhysicianById(appointmentUpdated.getDoctorId());
		
		/*
		 * jobService.addOutboxJob(new OutboxJob(OutboxJobType.APPOINTMENT, patient,
		 * physician, appointmentUpdated));
		 */ 
		
		return appDto;
	}

	@Override
	public List<AppointmentDto> getAppointments() {
		
		List<AppointmentDto> appointmentDtos = new ArrayList<>();
		List<Appointment> appointments = new ArrayList<>();
		
		User loggedInUser = (User) authService.getAuthentication().getDetails();
		
		if(loggedInUser.getUserType().equals(UserType.DOCTOR)) {
			appointments = appointmentRepository.getAppointmentByDoctorId(loggedInUser.getUserId());
			convertToAppointmentDto(appointmentDtos, appointments);
		}
		
		if(loggedInUser.getUserType().equals(UserType.PATIENT)) {
			appointments = appointmentRepository.getAppointmentByPatientId(loggedInUser.getUserId());
			convertToAppointmentDto(appointmentDtos, appointments);
		}
		
		return appointmentDtos;
	}
	
	
	private List<AppointmentDto> convertToAppointmentDto(List<AppointmentDto> appointmentDtos, List<Appointment> appointments) { 
		
		for(Appointment appointment : appointments) { 
			
			AppointmentDto appointmentDto = new AppointmentDto(); 
			appointmentDto.setPatientId(appointment.getPatientId());
			appointmentDto.setDoctorId(appointment.getDoctorId());
			appointmentDto.setSpeciality(appointment.getSpeciality());
			appointmentDto.setPaymentStripe(appointment.getPaymentStripe());
			appointmentDto.setVideoChannel(appointment.getVideoChannel());
			appointmentDto.setAppointmentDate(appointment.getAppointmentDate().toString());
			
			String dbSlot = appointment.getSlot();
			Type type = new TypeToken<Map<String, String>>(){}.getType();
			Map<String, String> myMap = new Gson().fromJson(dbSlot, type);
			Slot slot = new Slot();
			slot.setFrom(myMap.get("from"));
			slot.setTo(myMap.get("to"));
			appointmentDto.setSlot(slot);
			
			appointmentDto.setPayment(appointment.getPayment());
			appointmentDto.setRecordId(appointment.getRecordId());
			appointmentDto.setDoctorName(appointment.getDoctorName());
			appointmentDtos.add(appointmentDto);
		} 
		
		return appointmentDtos;
	}
	
private AppointmentDto convertToAppointmentDto(AppointmentDto appointmentDto, Appointment appointment) { 
		
			appointmentDto.setPatientId(appointment.getPatientId());
			appointmentDto.setDoctorId(appointment.getDoctorId());
			appointmentDto.setSpeciality(appointment.getSpeciality());
			appointmentDto.setPaymentStripe(appointment.getPaymentStripe());
			appointmentDto.setVideoChannel(appointment.getVideoChannel());
			appointmentDto.setAppointmentDate(appointment.getAppointmentDate().toString());
			
			String dbSlot = appointment.getSlot();
			Type type = new TypeToken<Map<String, String>>(){}.getType();
			Map<String, String> myMap = new Gson().fromJson(dbSlot, type);
			Slot slot = new Slot();
			slot.setFrom(myMap.get("from"));
			slot.setTo(myMap.get("to"));
			appointmentDto.setSlot(slot);
			
			appointmentDto.setPayment(appointment.getPayment());
			appointmentDto.setRecordId(appointment.getRecordId());
			appointmentDto.setDoctorName(appointment.getDoctorName());
		
			return appointmentDto;
	}

	protected  <T extends BaseEntity> T setDefaultOnCreate(T entity, User user){
		entity.setCreatedBy(user);
		entity.setCreatedOn(LocalDateTime.now());
		entity.setDeleted(false);
		return entity;
	}

	protected  <T extends BaseEntity> T setDefaultOnUpdate(T entity, User user){
		entity.setLastModifiedBy(user);
		entity.setLastModifiedOn(LocalDateTime.now());
		entity.setDeleted(false);
		return entity;
	}
	 

}

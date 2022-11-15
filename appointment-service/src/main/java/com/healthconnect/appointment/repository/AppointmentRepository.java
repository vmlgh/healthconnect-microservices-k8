package com.healthconnect.appointment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import com.healthconnect.appointment.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
		
	  @Nullable	  
	  @Query("SELECT a from Appointment a where a.patientId = :patientId and a.appointmentDate = :date and a.slot = :slot")
	  Appointment findAppointment(@Param("patientId") String patientId, @Param("date") LocalDate date, @Param("slot") String slot);
	  
	  @Modifying	  
	  @Query(value = "UPDATE Appointment a SET a.paymentStripe = :paymentUrl, a.videoChannel = :videoLink WHERE a.RecordId = :recordId", nativeQuery=true)
	  void updateAppointment(@Param("paymentUrl") String paymentUrl, @Param("videoLink") String videoLink, @Param("recordId") long recordId);
	  
	  @Nullable	  
	  @Query("SELECT a from Appointment a WHERE a.patientId = :patientId")
	  List<Appointment> getAppointmentByPatientId(@Param("patientId") String patientId);
	  
	  @Nullable	  
	  @Query("SELECT a from Appointment a WHERE a.doctorId = :doctorId")
	  List<Appointment> getAppointmentByDoctorId(@Param("doctorId") String doctorId);
	
}

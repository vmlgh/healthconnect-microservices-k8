package com.healthconnect.appointment.client;

import com.healthconnect.user.model.common.SpecialityMaster;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.physician.Physician;

public interface AppointmentServiceClient {
	
	User findUserById(long id);

	Physician findPhysicianById(String doctorId);

	SpecialityMaster findSpecialityByName(String name);

}

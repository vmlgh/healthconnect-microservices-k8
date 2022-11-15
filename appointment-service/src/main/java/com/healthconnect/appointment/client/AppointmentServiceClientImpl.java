package com.healthconnect.appointment.client;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.healthconnect.user.model.common.SpecialityMaster;
import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.physician.Physician;
import com.healthconnect.user.model.physician.PhysicianDto;
import com.healthconnect.user.model.util.ApiResponse;

@Service
public class AppointmentServiceClientImpl implements AppointmentServiceClient {
	
	private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceClientImpl.class);
	
	@Value("${user_service_url}")
	private String userServiceUrl;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public User findUserById(long id) {
		final String serviceUrl = userServiceUrl + "/user/" + Long.toString(id);
		
		ParameterizedTypeReference<ApiResponse<User>> myBean =
			     new ParameterizedTypeReference<ApiResponse<User>>() {};

			 ResponseEntity<ApiResponse<User>> response =
					 restTemplate.exchange(serviceUrl,HttpMethod.GET, null, myBean);

		User user = (User) response.getBody().getResult();
		return user;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Physician findPhysicianById(String doctorId) {
		
		final String serviceUrl = userServiceUrl + "/doctor/" + doctorId;
		
		ParameterizedTypeReference<ApiResponse<PhysicianDto>> myBean =
			     new ParameterizedTypeReference<ApiResponse<PhysicianDto>>() {};

			 ResponseEntity<ApiResponse<PhysicianDto>> response =
					 restTemplate.exchange(serviceUrl,HttpMethod.GET, null, myBean);
			 
			 logger.info("Making call to user service with url : " + serviceUrl); 
			 
		//ApiResponse<Physician> apiResponse = restTemplate.getForObject(serviceUrl, ApiResponse.class);
			 ApiResponse<PhysicianDto> healtConnectApiResponse = response.getBody();
		PhysicianDto physicianDto =  (PhysicianDto) healtConnectApiResponse.getResult();
		return convertToPhysician(physicianDto);
	}
	
	
	private Physician convertToPhysician(PhysicianDto physicianDto) {
		Physician physician  = new Physician();
		User user = new User();
		user.setFirstName(physicianDto.getFirstName());
		user.setEmail(physicianDto.getEmail());
		physician.setUser(user);
		physician.setRecordId(physicianDto.getRecordId());
		return physician;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public SpecialityMaster findSpecialityByName(String name) {
		final String serviceUrl = userServiceUrl + "/master/specialityMaster/" + name;	
		
		ParameterizedTypeReference<ApiResponse<SpecialityMaster>> myBean =
			     new ParameterizedTypeReference<ApiResponse<SpecialityMaster>>() {};

			 ResponseEntity<ApiResponse<SpecialityMaster>> response =
					 restTemplate.exchange(serviceUrl,HttpMethod.GET, null, myBean);
			 
		//ApiResponse<SpecialityMaster> apiResponse = restTemplate.getForObject(serviceUrl, ApiResponse.class);
		SpecialityMaster speciality = (SpecialityMaster) response.getBody().getResult();
		return speciality;
	}

}

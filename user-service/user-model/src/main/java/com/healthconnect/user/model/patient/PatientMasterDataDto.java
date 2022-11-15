package com.healthconnect.user.model.patient;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthconnect.user.model.common.BaseMasterDataDto;
import com.healthconnect.user.model.common.CommonDto;
import com.healthconnect.user.model.common.ServiceSearchDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientMasterDataDto extends BaseMasterDataDto {


	private List<CommonDto> bloodGroups;
	
	private List<ServiceSearchDto>  healthConnectServices;
	
	private List<CommonDto> genders;
	
	public List<CommonDto> getBloodGroups() {
		return bloodGroups;
	}

	public void setBloodGroups(List<CommonDto> bloodGroups) {
		this.bloodGroups = bloodGroups;
	}

	public List<ServiceSearchDto> getHealthConnectServices() {
		return healthConnectServices;
	}

	public void setHealthConnectServices(List<ServiceSearchDto> healthConnectServices) {
		this.healthConnectServices = healthConnectServices;
	}

	public List<CommonDto> getGenders() {
		return genders;
	}

	public void setGenders(List<CommonDto> genders) {
		this.genders = genders;
	}
}


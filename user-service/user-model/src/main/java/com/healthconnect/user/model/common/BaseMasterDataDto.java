package com.healthconnect.user.model.common;

import java.util.List;

public class BaseMasterDataDto  {

	
	//private List<AppSpecialityDto> specialities;
	
	private List<SimpleDto> services;

	/*
	 * public List<AppSpecialityDto> getSpecialities() { return specialities; }
	 * 
	 * public void setSpecialities(List<AppSpecialityDto> specialities) {
	 * this.specialities = specialities; }
	 */
	public List<SimpleDto> getServices() {
		return services;
	}

	public void setServices(List<SimpleDto> services) {
		this.services = services;
	}
}


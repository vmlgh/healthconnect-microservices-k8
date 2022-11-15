package com.healthconnect.user.model.physician;

import javax.validation.constraints.NotEmpty;

import com.healthconnect.user.model.common.AddressDto;

public class PhysicianHospitalDto {

    private long hospitalId;

    @NotEmpty(message ="{hospital.name.empty.error}")
    private String name;

    //@NotNull(message ="{address.null.error}")
    private AddressDto address;

    //private boolean delete;

    public long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	/*
	 * public boolean isDelete() { return delete; }
	 * 
	 * public void setDelete(boolean delete) { this.delete = delete; }
	 */

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
}


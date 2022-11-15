package com.healthconnect.user.model.common;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {

	@NotEmpty(message="{address.country.empty.error}")
	private String country;

	@NotEmpty(message="{address.state.empty.error}")
	private String state;

	@NotEmpty(message="{address.district.empty.error}")
	private String district;

	@NotEmpty(message="{address.city.empty.error}")
	private String city;
	
	private String fullAddress;

	@NotNull(message="{address.pincode.null.error}")
	private String pinCode;

	private String locality;

	public AddressDto(String locality, String city, String pinCode) {
        this.locality = locality;
        this.city = city;
        this.pinCode = pinCode;
	}

	public AddressDto() {

    }

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
}

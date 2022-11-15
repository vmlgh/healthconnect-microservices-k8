package com.healthconnect.user.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;

@Entity
@Table(name ="Address")
public class Address extends BaseEntity {

	@Column(name="Country")
	private String country;
	
	@Column(name ="State")
	private String state;
	
	@Column(name ="District")
	private String district;
	
	@Column(name="City")
	private String city;
	
	@OneToOne
	@JoinColumn(name ="CityId")
	private CityMaster cityMaster;
	
	@Column(name ="FullAddress")
	private String fullAddress;
	
	@Column(name ="Pin")
	private String  pinCode;
	
	
	@Column(name ="Locality")
	private String locality;

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

	public CityMaster getCityMaster() {
		return cityMaster;
	}

	public void setCityMaster(CityMaster cityMaster) {
		this.cityMaster = cityMaster;
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

	public AddressDto toAddressDto() {
		AddressDto addressDto  = new AddressDto(this.locality,this.city,this.pinCode);
		addressDto.setCountry(this.country);
		addressDto.setFullAddress(this.fullAddress);
		return addressDto;
	}
}


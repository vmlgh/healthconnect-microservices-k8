package com.healthconnect.user.model.common;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;

@Entity
@Table(name ="StateMaster")
public class StateMaster extends BaseEntity {
	
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	@Column(name ="Name", unique = true)
	private String name;
	
	@ManyToOne
	@JoinColumn(name ="CountryId")
	private CountryMaster country;
	
	@OneToMany(mappedBy="state")
	private List<CityMaster> cities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CountryMaster getCountry() {
		return country;
	}

	public void setCountry(CountryMaster country) {
		this.country = country;
	}

	public List<CityMaster> getCities() {
		return cities;
	}

	public void setCities(List<CityMaster> cities) {
		this.cities = cities;
	}
}


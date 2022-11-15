package com.healthconnect.user.model.common;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;

@Entity
@Table(name ="SpecialityMaster")
public class SpecialityMaster extends BaseEntity {

	@Column(name ="Name", unique = true)
	private String name;
	
	@Column(name ="Description")
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy ="specialityMaster")
	private Set<SubSpecialityMaster> subSpecialityMasters;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<SubSpecialityMaster> getSubSpecialityMasters() {
		return subSpecialityMasters;
	}

	public void setSubSpecialityMasters(Set<SubSpecialityMaster> subSpecialityMasters) {
		this.subSpecialityMasters = subSpecialityMasters;
	}
}

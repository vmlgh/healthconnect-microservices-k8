package com.healthconnect.user.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;

@Entity
@Table(name = "MedicalCouncilMaster")
public class MedicalCouncilMaster extends BaseEntity {
	
	public MedicalCouncilMaster() {
		
	}
	public MedicalCouncilMaster(String name) {
		this.name = name;
	}

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	@Column(name = "Name", unique = true)
	private String name;
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}


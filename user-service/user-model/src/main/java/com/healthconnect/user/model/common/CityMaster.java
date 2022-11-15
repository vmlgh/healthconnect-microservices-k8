package com.healthconnect.user.model.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;

@Entity
@Table(name ="CityMaster")
public class CityMaster  extends BaseEntity {

	
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	@Column(name ="Name", unique = true)
	private String name;

	@Column(name ="StateName")
	private String stateName;
	
	@ManyToOne
	@JoinColumn(name ="StateId")
	private StateMaster state;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StateMaster getState() {
		return state;
	}

	public void setState(StateMaster state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
}


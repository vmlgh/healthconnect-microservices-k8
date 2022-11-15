package com.healthconnect.user.model.common;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.healthconnect.user.model.core.BaseEntity;

@Entity
@Table(name ="CountryMaster")
public class CountryMaster extends BaseEntity {

	
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	@Column(name ="Name", unique = true)
	private String name;
	
	@OneToMany(mappedBy ="country")
	private List<StateMaster> states;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StateMaster> getStates() {
		return states;
	}

	public void setStates(List<StateMaster> states) {
		this.states = states;
	}
}


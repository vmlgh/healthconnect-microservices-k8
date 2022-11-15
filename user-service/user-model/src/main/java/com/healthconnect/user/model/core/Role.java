package com.healthconnect.user.model.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.healthconnect.user.model.enums.RoleType;


/**
 * The Class Role.
 */
@Entity
@Table(name ="Roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
	@Column(name ="Name", unique = true)
	private RoleType name;
	
	@Column(name ="Description")
	private String description;

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}


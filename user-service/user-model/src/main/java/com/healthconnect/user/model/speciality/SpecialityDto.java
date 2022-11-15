package com.healthconnect.user.model.speciality;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.healthconnect.user.model.common.CommonDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecialityDto {

    private long id;
    private String name;
	private String description;
	
	private Set<CommonDto> subSpecialityMasters;

    public SpecialityDto(long id, String name){
        this.name = name;
        this.id = id;
    }
    public SpecialityDto() {
    	
    }

    public long getId() {
        return id;
    }
    
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<CommonDto> getSubSpecialityMasters() {
		return subSpecialityMasters;
	}
	public void setSubSpecialityMasters(Set<CommonDto> subSpecialityMasters) {
		this.subSpecialityMasters = subSpecialityMasters;
	}

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}


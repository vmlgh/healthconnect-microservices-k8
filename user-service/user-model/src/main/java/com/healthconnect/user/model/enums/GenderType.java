package com.healthconnect.user.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderType {
	
	MALE("M"), FEMALE("F");
	
	private String value;
	
	
	private GenderType(String value){
		this.value = value;
	}
	
	public static GenderType fromValue(String value){
        for(GenderType r : GenderType.values()){
            if(r.getValue().equals(value)){
                return r;
            }
        }
        throw new IllegalArgumentException();
    }
	
	
	@JsonValue
	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}

}


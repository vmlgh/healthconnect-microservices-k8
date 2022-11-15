package com.healthconnect.user.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public enum UserType {

	@JsonProperty("Doctor")
    DOCTOR("Doctor"),
    
    @JsonProperty("Patient")
    PATIENT("Patient");

    private String value;


    UserType(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static UserType fromValue(String value) {
        for (UserType UserType : values()) {
            if (UserType.value.equalsIgnoreCase(value)) {
                return UserType;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static UserType create (String value) {
        if(value == null) {
            throw new IllegalArgumentException();
        }
        for(UserType v : values()) {
            if(value.equals(v.value)) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return value;
    }

}

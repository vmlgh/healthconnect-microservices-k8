package com.healthconnect.user.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum HospitalType {

    Private,Government;
    
    public static List<String> getEnumsAsString(){
    	List<String> enumList = new ArrayList<>();
    	for(HospitalType hospitalType : HospitalType.values()) {
    		enumList.add(hospitalType.name());
    	}
    	return enumList;
    }
}


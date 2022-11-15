package com.healthconnect.user.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PricingUnit {

	PER_HOUR("Per Hour"),PER_VISIT("Per Visit");
	
	private String value;

	private PricingUnit(String value){
		this.value = value;
	}
	
	public static List<String> valuesAsList() {
		return Arrays.stream(PricingUnit.values()).map(PricingUnit::getValue)
        .collect(Collectors.toList());
	}
	
	
	
	public static PricingUnit fromValue(String value){
        for(PricingUnit r : PricingUnit.values()){
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

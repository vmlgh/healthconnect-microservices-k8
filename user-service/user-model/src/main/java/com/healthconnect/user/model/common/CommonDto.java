package com.healthconnect.user.model.common;


public class CommonDto {
	
	private String key;
	
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CommonDto() {

	}

	public CommonDto(String key, String value) {
		this.key = key;
		this.value = value;
	}
	

}


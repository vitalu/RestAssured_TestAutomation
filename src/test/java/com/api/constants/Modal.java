package com.api.constants;

public enum Modal {
	NEXUS_BLUE_2(1), GALAXY(2);
	
	int code;
	Modal(int code){
		this.code = code;
	}
	public int getCode() {
		return code;
	}

}

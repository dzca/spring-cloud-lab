package ca.ocbl.common.enums;

public enum UserStatuEnum {
	UNREGISTERED(1), 
	REGISTERED(2), 
	FREE(3);

	private final int code;

	private UserStatuEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}

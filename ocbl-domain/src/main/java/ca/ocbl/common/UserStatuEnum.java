package ca.ocbl.common;

public enum UserStatuEnum {
	UNREGISTERED(1, "unregistered"), 
	REGISTERED(2, "registered"), 
	FREE(3, "free");

	private final int code;
	private final String name;

	private UserStatuEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}

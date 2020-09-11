package com.company;

public enum CONFIRMATION {

	YES("ДА"),
	OK("ок"),
	OKAY("окей"),
	SURE("уверен"),
	YA("точно"),
	OFCOURSE("конечно");

	private String value;

	CONFIRMATION(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static boolean isConfirmation(String value) {
		for (CONFIRMATION confirmation : CONFIRMATION.values()) {
			return confirmation.value.equalsIgnoreCase(value);
		}
		return false;
	}
}

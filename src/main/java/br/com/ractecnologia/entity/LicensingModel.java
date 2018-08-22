package br.com.ractecnologia.entity;

import java.util.Arrays;

public enum LicensingModel {

	TWO_DAYS(1), LIFE_LONG(2);

	private final int value;

	private LicensingModel(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static LicensingModel get(Integer value) {
		return Arrays.asList(values()).stream().filter(c -> c.getValue() == value).findAny().orElse(null);
	}
	
	public static LicensingModel get(String name) {
		return Arrays.asList(values()).stream().filter(c -> c.name().equals(name)).findAny().orElse(null);
	}

}

package br.com.ractecnologia.entity;

import java.util.Arrays;

public enum CustomerStatus {

	REGULAR(1,"R"), ADVANCED(2,"A");

	private final Integer value;
	
	private final String simbol;

	private CustomerStatus(int value, String simbol) {
		this.value = value;
		this.simbol = simbol;
	}

	public Integer getValue() {
		return value;
	}
	
	public String getSimbol() {
		return simbol;
	}

	public static CustomerStatus get(Integer value) {
		return Arrays.asList(values()).stream().filter(c -> c.getValue() == value).findAny().orElse(null);
	}
	
	public static CustomerStatus get(String name) {
		return Arrays.asList(values()).stream().filter(c -> c.name().equals(name)).findAny().orElse(null);
	}

}

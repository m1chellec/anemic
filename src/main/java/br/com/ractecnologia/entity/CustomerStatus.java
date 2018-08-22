package br.com.ractecnologia.entity;

import java.util.Arrays;

public enum CustomerStatus {

	REGULAR(1), ADVANCED(2);

	private final Integer value;

	private CustomerStatus(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	public static CustomerStatus get(Integer value) {
		return Arrays.asList(values()).stream().filter(c -> c.getValue() == value).findAny().orElse(null);
	}
	
	public static CustomerStatus get(String name) {
		return Arrays.asList(values()).stream().filter(c -> c.name().equals(name)).findAny().orElse(null);
	}

}

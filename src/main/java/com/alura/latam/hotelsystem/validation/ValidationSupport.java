package com.alura.latam.hotelsystem.validation;

public class ValidationSupport {

	public boolean isNullOrEmptyString(String value) {
		return (value.isBlank() || value == null); 
	}
	
	public boolean isNullValue(Object value) {
		return value == null;
	}
	
	public boolean isValueLessThanZero(Double value) {
		return value <= 0;
	}
}

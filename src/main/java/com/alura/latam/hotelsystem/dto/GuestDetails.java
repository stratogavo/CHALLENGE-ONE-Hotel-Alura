package com.alura.latam.hotelsystem.dto;

import java.time.LocalDate;

import com.alura.latam.hotelsystem.model.Guest;

public record GuestDetails(
		Long id,
		String name,
		String surname,
		LocalDate dateOfBirth,
		String nationality,
		String number,
		String validationError) {
	
	public GuestDetails(Guest guest) {
		this(
				guest.getId(),
				guest.getName(),
				guest.getSurname(),
				guest.getDateOfBirth(),
				guest.getNationality(),
				guest.getPhoneNumber(),
				null
				);
		
	}
	
	public GuestDetails(String validationError) {
		this(null, null, null, null, null, null, validationError);
	}

}

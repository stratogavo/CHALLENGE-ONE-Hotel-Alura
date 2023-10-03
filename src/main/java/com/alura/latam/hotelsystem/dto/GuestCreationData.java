package com.alura.latam.hotelsystem.dto;

import java.time.LocalDate;

public record GuestCreationData(
		String name,
		String surname,
		LocalDate dateOfBirth,
		String nationality,
		String phoneNumber
		) {

}

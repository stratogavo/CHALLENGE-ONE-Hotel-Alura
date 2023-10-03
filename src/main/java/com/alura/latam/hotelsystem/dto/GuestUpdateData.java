package com.alura.latam.hotelsystem.dto;

import java.time.LocalDate;

public record GuestUpdateData(
		String name,
		String surname,
		LocalDate dateOfBirth,
		String nationality,
		String phoneNumber
		) {}

package com.alura.latam.hotelsystem.dto;

import java.time.LocalDate;

public record GuestReportRecord(
		Long id,
		String name,
		String surname,
		LocalDate dateOfBirth,
		String nationality,
		String phoneNumber,
		String reservationNumber
		) {}

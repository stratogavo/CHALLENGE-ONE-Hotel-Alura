package com.alura.latam.hotelsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.alura.latam.hotelsystem.model.Reservation;

public record ReservationDetails(
		Long id,
		String reservationNumber,
		LocalDate checkInDate,
		LocalDate checkOutDate,
		BigDecimal amount,
		String paymentType,
		String validationError
		) {
	
	public ReservationDetails(Reservation reservation) {
		this(
				reservation.getId(),
				reservation.getReserveNumber(),
				reservation.getCheckInDate(),
				reservation.getCheckOutDate(),
				reservation.getAmount(),
				reservation.getPaymentType(),
				null);
	}
	
	public ReservationDetails(String validationError) {
		this(null, null, null, null, null, null, validationError);
	}
	
}

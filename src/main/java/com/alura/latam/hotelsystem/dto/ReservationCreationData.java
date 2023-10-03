package com.alura.latam.hotelsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationCreationData(
		String reservationNumber,
		LocalDate checkInDate,
		LocalDate checkOutDate,
		BigDecimal amount,
		String paymentType
		) {}

package com.alura.latam.hotelsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationUpdateData(
		LocalDate checInDate,
		LocalDate checkOutDate,
		BigDecimal amount,
		String paymentType) {}

package com.alura.latam.hotelsystem.validation;

import java.time.LocalDate;
import java.util.Optional;

import com.alura.latam.hotelsystem.model.Reservation;

public class ReservationValidation extends ValidationSupport
	implements Validator<Reservation> {

	@Override
	public Optional<ValidationError> validate(Reservation reservation) {
		if (isNullValue(reservation.getCheckInDate()) ||
				isNullValue(reservation.getCheckOutDate())) {
			return Optional.of(
					new ValidationError("Datos requeridos sin llenar o incorrectos"));
		}
		if (reservation.getCheckInDate().isBefore(LocalDate.now())
				|| reservation.getCheckOutDate().isBefore(LocalDate.now())) {
			return Optional.of(
					new ValidationError("La fecha de entrada o salida no pueder ser "
							+ "anterior al día de hoy"));
		}
		if (reservation.getCheckInDate().isAfter(reservation.getCheckOutDate())) {
			return Optional.of(
					new ValidationError("La fecha del check-in debe ser anterior al "
							+ "check-out"));
		}
		if (reservation.getCheckInDate().equals(reservation.getCheckOutDate())) {
			return Optional.of(
					new ValidationError("La fecha del check-in no deber ser igual a "
							+ "la fecha del check-out"));
		}
		return Optional.empty();
	}

}

package com.alura.latam.hotelsystem.validation;

import java.time.LocalDate;
import java.util.Optional;

import com.alura.latam.hotelsystem.model.Guest;

public class GuestValidation extends ValidationSupport 
	implements Validator<Guest>{

	private final String phoneRegexValidator = 
			"^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
	
	@Override
	public Optional<ValidationError> validate(Guest guest) {
		if (isNullOrEmptyString(guest.getName())
				|| isNullOrEmptyString(guest.getSurname())
				|| isNullValue(guest.getDateOfBirth())
				|| isNullOrEmptyString(guest.getNationality())
				|| isNullOrEmptyString(guest.getPhoneNumber())) {
			return Optional.of(
					new ValidationError("Datos requeridos sin llenar o incorrectos"));
		}
		if (!guest.getPhoneNumber().matches(phoneRegexValidator)) {
			return Optional.of(
					new ValidationError("El formato del número telefónico es inválido"));
		}
		if (guest.getDateOfBirth().isAfter(LocalDate.now().minusYears(18))) {
			return Optional.of(
					new ValidationError("El huésped debe tener la mayoría de edad"));
		}
		return Optional.empty();
	}

}

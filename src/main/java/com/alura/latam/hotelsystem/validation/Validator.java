package com.alura.latam.hotelsystem.validation;

import java.util.Optional;

public interface Validator<K> {

	Optional<ValidationError> validate(K k);
	
}

package com.alura.latam.hotelsystem.service;

import java.util.List;
import java.util.Optional;

import com.alura.latam.hotelsystem.dao.GuestDAO;
import com.alura.latam.hotelsystem.dto.GuestCreationData;
import com.alura.latam.hotelsystem.dto.GuestDetails;
import com.alura.latam.hotelsystem.dto.GuestReportRecord;
import com.alura.latam.hotelsystem.model.Guest;
import com.alura.latam.hotelsystem.validation.GuestValidation;
import com.alura.latam.hotelsystem.validation.ValidationError;

public class GuestService {

	private GuestDAO guestDAO;
	
	private GuestValidation validator;
	
	public GuestService() {
		guestDAO = new GuestDAO();
		validator = new GuestValidation();
	}
	
	public GuestDetails save(GuestCreationData creationData) {
		var guest = new Guest(creationData);
		Optional<ValidationError> error = validator.validate(guest);
		if (error.isPresent()) {
			return new GuestDetails(error.get().getMessage());
		} else {
			guestDAO.create(guest);
			return new GuestDetails(guest);
		}
	}

	public GuestDetails updateGuest(Guest guest) {
		Optional<ValidationError> error = validator.validate(guest);
		if (error.isPresent()) {
			return new GuestDetails(error.get().getMessage());
		} else {
			guestDAO.update(guest);
			return new GuestDetails(guest);
		}
	}
	
	public void deleteGuest(Guest guest) {
		guest.setActive(false);
		guestDAO.update(guest);
	}
	
	public Guest findById(Long id) {
		if (id == null) {
			return null;
		}
		return guestDAO.getReferenceById(id);
	}

	public List<GuestReportRecord> findAllActiveGuests() {
		return guestDAO.findAllByActiveTrue();
	}
	
	public List<GuestReportRecord> findGuestBySurname(String surname) {
		if (surname.isBlank()) {
			return null;
		}
		return guestDAO.findBySurname(surname);
	}
	
	public void closeEntityManager() {
		guestDAO.getEm().close();
	}

}

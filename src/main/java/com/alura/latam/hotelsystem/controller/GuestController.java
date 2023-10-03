package com.alura.latam.hotelsystem.controller;

import java.util.List;

import com.alura.latam.hotelsystem.dto.GuestCreationData;
import com.alura.latam.hotelsystem.dto.GuestDetails;
import com.alura.latam.hotelsystem.dto.GuestReportRecord;
import com.alura.latam.hotelsystem.dto.GuestUpdateData;
import com.alura.latam.hotelsystem.model.Guest;
import com.alura.latam.hotelsystem.service.GuestService;

public class GuestController {

	private GuestService guestService;
	
	public GuestController() {
		guestService = new GuestService();
	}
	
	public GuestDetails saveEntity(
			GuestCreationData creationData) {
		return guestService.save(creationData);
	}
	
	public Guest findEntityById(Long id) {
		return guestService.findById(id);
	}
	
	public GuestDetails updateEntity(Guest guest, GuestUpdateData updateData) {
		var updatedGuest = new Guest(guest.getId(), updateData);
		return guestService.updateGuest(updatedGuest);
	}
	
	public void deleteEntity(Guest guest) {
		guestService.deleteGuest(guest);
	}
	
	public List<GuestReportRecord> getGuestsReport() {
		return guestService.findAllActiveGuests();
	}
	
	public List<GuestReportRecord> searchGuestSurname(String surname) {
		return guestService.findGuestBySurname(surname);
	}
	
	public void closeConnection() {
		guestService.closeEntityManager();
	}
	
}

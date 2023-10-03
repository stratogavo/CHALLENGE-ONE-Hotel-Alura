package com.alura.latam.hotelsystem.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.alura.latam.hotelsystem.dto.ReservationCreationData;
import com.alura.latam.hotelsystem.dto.ReservationDetails;
import com.alura.latam.hotelsystem.dto.ReservationUpdateData;
import com.alura.latam.hotelsystem.model.Guest;
import com.alura.latam.hotelsystem.model.Reservation;
import com.alura.latam.hotelsystem.service.ReservationService;

public class ReservationController {
	
	private ReservationService reservationService;
	
	public ReservationController() {
		reservationService = new ReservationService();
	}
	
	public ReservationDetails saveEntity(
			ReservationCreationData creationData) {
		return reservationService.save(creationData);
	}
	
	public void addGuest(Reservation reservation, Guest guest) {
		reservationService.updateGuest(reservation, guest);
	}
	
	public ReservationDetails updateEntity(Reservation reservation, 
			ReservationUpdateData updateData) {
		var updatedReservation = new Reservation(reservation.getId(), 
				reservation.getReserveNumber(), updateData);
		return reservationService.updateReservation(updatedReservation);
	}
	
	public void deleteEntity(Reservation reservation) {
		reservationService.deleteReservation(reservation);
	}
	
	public Reservation getEntityById(Long id) {
		return reservationService.findById(id);
	}
	
	public List<Reservation> getReservationsReport() {
		return reservationService.findAllActiveReservations();
	}
	
	public List<Reservation> searchReservationNumber(String number) {
		return reservationService.findReservationByNumber(number);
	}
	
	public BigDecimal getAmount(LocalDate checkIn, LocalDate checkOut) {
		return reservationService.calculateAmount(checkIn, checkOut);
	}
	
	public String getNumber() {
		return reservationService.generateReservationNumber();
	}
	
	public void closeConnection() {
		reservationService.closeEntityManager();
	}
}

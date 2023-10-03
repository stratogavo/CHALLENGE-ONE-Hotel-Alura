package com.alura.latam.hotelsystem.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.alura.latam.hotelsystem.dao.GuestDAO;
import com.alura.latam.hotelsystem.dao.ReservationDAO;
import com.alura.latam.hotelsystem.dto.ReservationCreationData;
import com.alura.latam.hotelsystem.dto.ReservationDetails;
import com.alura.latam.hotelsystem.model.Guest;
import com.alura.latam.hotelsystem.model.Reservation;
import com.alura.latam.hotelsystem.validation.ReservationValidation;
import com.alura.latam.hotelsystem.validation.ValidationError;

import lombok.Getter;

@Getter
public class ReservationService {

	private final Double HOST_RATE = 1000.00;
	
	private final Integer MAX_RESERVE_NUMBER_SERIE_A = 9999;
	private final Integer MAX_RESERVE_NUMBER_SERIE_B = 999999;
	
	private ReservationDAO reservationDAO;
	private GuestDAO guestDAO;
	
	private ReservationValidation validator;
	
	public ReservationService() {
		reservationDAO = new ReservationDAO();
		guestDAO = new GuestDAO();
		validator = new ReservationValidation();
	}
 	
	public ReservationDetails save(ReservationCreationData creationData) {
		var reservation = new Reservation(creationData);
		Optional<ValidationError> error = validator.validate(reservation);
		if (error.isPresent()) {
			return new ReservationDetails(error.get().getMessage());
		} else {
			reservationDAO.create(reservation);
			return new ReservationDetails(reservation);
		}
	}
	
	public ReservationDetails updateReservation(Reservation reservation) {
		Optional<ValidationError> error = validator.validate(reservation);
		if (error.isPresent()) {
			return new ReservationDetails(error.get().getMessage());
		} else {
			reservationDAO.update(reservation);
			return new ReservationDetails(reservation);
		}
	}
	
	public void updateGuest(Reservation reservation, Guest guest) {
		var updatedRerservationWithGuest = reservationDAO
				.getReferenceById(reservation.getId());
		updatedRerservationWithGuest.setGuest(guest);
		reservationDAO.update(updatedRerservationWithGuest);
	}
	
	public void deleteReservation(Reservation reservation) {
		reservation.setActive(false);
		reservationDAO.update(reservation);
	}
	
	public Reservation findById(Long id) {
		if (id == null) {
			return null;
		}
		return reservationDAO.getReferenceById(id);
	}
	
	public List<Reservation> findAllActiveReservations() {
		return reservationDAO.findAllByActiveTrue();
	}
	
	public List<Reservation> findReservationByNumber(String number) {
		if (number.isBlank()) {
			return null;
		}
		return reservationDAO.findByReserveNumber(number);
	}
	
	public BigDecimal calculateAmount(LocalDate checkIn, LocalDate checkOut) {
		if (checkIn == null || checkOut == null) {
			return null;
		} 
		return new BigDecimal(
				HOST_RATE * ChronoUnit.DAYS.between(checkIn, checkOut));
	}
	
	public String generateReservationNumber() {
		var serieA = new Random();
		var serieB = new Random();
		var randNumberSerieA = serieA.nextInt(MAX_RESERVE_NUMBER_SERIE_A);
		var randNumberSerieB = serieB.nextInt(MAX_RESERVE_NUMBER_SERIE_B);
		var reservationNumber = "ALU-" + String.valueOf(randNumberSerieA) 
			+ "-" + String.valueOf(randNumberSerieB);
		return reservationNumber;
	}

	public void closeEntityManager() {
		reservationDAO.getEm().close();
	}

}
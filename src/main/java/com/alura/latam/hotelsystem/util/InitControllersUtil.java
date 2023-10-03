package com.alura.latam.hotelsystem.util;

import com.alura.latam.hotelsystem.controller.GuestController;
import com.alura.latam.hotelsystem.controller.ReservationController;

public class InitControllersUtil {
	
	public static ReservationController getReservationController(
			ReservationController reservationController) {
		if (reservationController == null) {
			return new ReservationController();
		} else {
			return reservationController;
		}
	}
	
	public static GuestController getGuestController(
			GuestController guestController) {
		if (guestController == null) {
			return new GuestController();
		} else {
			return guestController;
		}
	}

}

package com.alura.latam.hotelsystem.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alura.latam.hotelsystem.dto.ReservationCreationData;
import com.alura.latam.hotelsystem.dto.ReservationDetails;
import com.alura.latam.hotelsystem.dto.ReservationUpdateData;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "reservations")
@Entity(name = "Reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "reserve_number")
	private String reserveNumber;
	
	@Column(name = "check_in_date")
	private LocalDate checkInDate;
	
	@Column(name = "check_out_date")
	private LocalDate checkOutDate;
	
	private BigDecimal amount;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@JoinColumn(name = "guest_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Guest guest;
	
	private Boolean active;
	
	public Reservation(ReservationCreationData creationData) {
		this.reserveNumber = creationData.reservationNumber();
		this.checkInDate = creationData.checkInDate();
		this.checkOutDate = creationData.checkOutDate();
		this.amount = creationData.amount();
		this.paymentType = creationData.paymentType();
		this.active = true;
	}
	
	public Reservation(Long id, String reserveNumer, 
			ReservationUpdateData updateData) {
		this.id = id;
		this.reserveNumber = reserveNumer;
		this.checkInDate = updateData.checInDate();
		this.checkOutDate = updateData.checkOutDate();
		this.amount = updateData.amount();
		this.paymentType = updateData.paymentType();
		this.active = true;
	}
	
	public Reservation(ReservationDetails reservationDetails) {
		this.id = reservationDetails.id();
		this.reserveNumber = reservationDetails.reservationNumber();
		this.checkInDate = reservationDetails.checkInDate();
		this.checkOutDate = reservationDetails.checkOutDate();
		this.amount = reservationDetails.amount();
		this.paymentType = reservationDetails.paymentType();
		this.active = true;
	}
	
}

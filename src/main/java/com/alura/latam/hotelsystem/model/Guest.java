package com.alura.latam.hotelsystem.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alura.latam.hotelsystem.dto.GuestCreationData;
import com.alura.latam.hotelsystem.dto.GuestDetails;
import com.alura.latam.hotelsystem.dto.GuestUpdateData;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "guests")
@Entity(name = "Guest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String surname;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	private String nationality;
	
	@Column(name = "phone_number")
	private String phoneNumber;

	private Boolean active;
	
	public Guest(GuestCreationData creationData) {
		this.name = creationData.name();
		this.surname = creationData.surname();
		this.dateOfBirth = creationData.dateOfBirth();
		this.nationality = creationData.nationality();
		this.phoneNumber = creationData.phoneNumber();
		this.active = true;
	}
	
	public Guest(GuestDetails guestDetails) {
		this.id = guestDetails.id();
		this.name = guestDetails.name();
		this.surname = guestDetails.surname();
		this.dateOfBirth = guestDetails.dateOfBirth();
		this.nationality = guestDetails.nationality();
		this.phoneNumber = guestDetails.number();
		this.active = true;
	}
	
	public Guest(Long id, GuestUpdateData updateData) {
		this.id = id;
		this.name = updateData.name();
		this.surname = updateData.surname();
		this.dateOfBirth = updateData.dateOfBirth();
		this.nationality = updateData.nationality();
		this.phoneNumber = updateData.phoneNumber();
		this.active = true;
	}
	
}

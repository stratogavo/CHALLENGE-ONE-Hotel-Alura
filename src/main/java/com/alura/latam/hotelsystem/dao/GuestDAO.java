package com.alura.latam.hotelsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.alura.latam.hotelsystem.dto.GuestReportRecord;
import com.alura.latam.hotelsystem.model.Guest;
import com.alura.latam.hotelsystem.util.EntittyFactory;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class GuestDAO {

	private EntityManager em;
	
	@Getter(value = AccessLevel.NONE)
	private final String GUEST_REPORT_DTO =
			"new com.alura.latam.hotelsystem.dto.GuestReportRecord";
	
	public GuestDAO() {
		em = EntittyFactory.getEntityManager();
	}
	
	public void create(Guest guest) {
		em.getTransaction().begin();
		em.persist(guest);
		em.getTransaction().commit();
		em.detach(guest);;
	}
	
	public void update(Guest guest) {
		em.getTransaction().begin();
		em.merge(guest);
		em.getTransaction().commit();
		em.detach(guest);;
	}
	
	public Guest getReferenceById(Long id) {
		return em.find(Guest.class, id);
	}

	public List<GuestReportRecord> findAllByActiveTrue() {
		var jpql = "SELECT "
				+ GUEST_REPORT_DTO
				+ "(g.id, g.name, g.surname, g.dateOfBirth,"
				+ "g.nationality, g.phoneNumber, r.reserveNumber) "
				+ "FROM Reservation r "
				+ "JOIN r.guest g "
				+ "WHERE g.active=true "
				+ "AND r.active=true";
		return em.createQuery(jpql, GuestReportRecord.class).getResultList();
	}
	
	public List<GuestReportRecord> findBySurname(String surname) {
		var jpql = "SELECT "
				+ GUEST_REPORT_DTO
				+ "(g.id, g.name, g.surname, g.dateOfBirth,"
				+ "g.nationality, g.phoneNumber, r.reserveNumber) "
				+ "FROM Reservation r "
				+ "JOIN r.guest g "
				+ "WHERE g.active=true "
				+ "AND r.active=true "
				+ "AND g.surname LIKE CONCAT(:surname, '%')";
		return em.createQuery(jpql, GuestReportRecord.class)
				.setParameter("surname", surname).getResultList();
	}
	
}

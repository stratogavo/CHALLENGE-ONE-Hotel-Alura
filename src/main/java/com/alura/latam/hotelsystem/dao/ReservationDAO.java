package com.alura.latam.hotelsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.alura.latam.hotelsystem.model.Reservation;
import com.alura.latam.hotelsystem.util.EntittyFactory;

import lombok.Getter;

@Getter
public class ReservationDAO {

	private EntityManager em;
	
	public ReservationDAO() {
		em = EntittyFactory.getEntityManager();
	}
	
	public void create(Reservation reservation) {
		em.getTransaction().begin();
		em.persist(reservation);
		em.getTransaction().commit();
		em.detach(reservation);
	}
	
	public void update(Reservation reservation) {
		em.getTransaction().begin();
		em.merge(reservation);
		em.getTransaction().commit();
		em.detach(reservation);
	}
	
	public Reservation getReferenceById(Long id) {
		return em.find(Reservation.class, id);
	}
	
	public List<Reservation> findAllByActiveTrue() {
		var jpql = "SELECT R FROM Reservation R WHERE R.active=true";
		return em.createQuery(jpql, Reservation.class).getResultList();
	}
	
	public List<Reservation> findByReserveNumber(String number) {
		var jpql = "SELECT R FROM Reservation R "
				+ "WHERE R.active=true AND R.reserveNumber=:number ";
		return em.createQuery(jpql, Reservation.class)
				.setParameter("number", number).getResultList();
	}
	
}

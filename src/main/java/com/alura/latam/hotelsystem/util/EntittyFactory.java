package com.alura.latam.hotelsystem.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntittyFactory {

	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("alurahotel");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}

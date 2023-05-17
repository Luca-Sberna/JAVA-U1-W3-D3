package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.Partecipazione;

public class PartecipazioneDAO {
	private EntityManagerFactory entityManagerFactory;

	public PartecipazioneDAO(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void save(Partecipazione partecipazione) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(partecipazione);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public Partecipazione getById(UUID uuid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Partecipazione persona = entityManager.find(Partecipazione.class, uuid);
		entityManager.close();
		return persona;
	}

	public void delete(Partecipazione partecipazione) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		partecipazione = entityManager.merge(partecipazione);
		entityManager.remove(partecipazione);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void refresh(Partecipazione partecipazione) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		partecipazione = entityManager.merge(partecipazione);
		entityManager.refresh(partecipazione);
		entityManager.close();
	}
}

package localdbservice.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import localdbservice.rest.dao.LifeCoachDao;

@Entity
@Table(name = "Level")
@NamedQuery(name = "Level.findAll", query = "SELECT l FROM Level l")
public class Level implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idLevel")
	private int idLevel;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "nGoalNecessary")
	private int nGoalNecessary;
	
	public int getIdLevel() {
		return this.idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getNGoalNecessary() {
		return this.nGoalNecessary;
	}

	public void setNGoalNecessary(int nGoalNecessary) {
		this.nGoalNecessary = nGoalNecessary;
	}

	// Database operations
	public static Level getLevelById(int levelId) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Level l = em.find(Level.class, levelId);
		LifeCoachDao.instance.closeConnections(em);
		return l;
	}
	
	public static List<Level> getAll() {
		System.out.println("--> Initializing Entity manager...");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Level> list = em.createNamedQuery("Level.findAll", Level.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Level saveLevel(Level l) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(l);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return l;
	}
	
	public static Level updateLevel(Level l) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		l=em.merge(l);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return l;
	}
	
	public static void removeLevel(Level l) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		l=em.merge(l);
	    em.remove(l);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}

package hr.primefaces.dao.impl;

import hr.primefaces.dao.ITheaterDAO;
import hr.primefaces.model.Theater;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TheaterDAO implements ITheaterDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	@Override
	public void addTheater(Theater theater) {
		getSessionFactory().getCurrentSession().save(theater);
	}

	@Override
	public void deleteTheater(Theater theater) {
		getSessionFactory().getCurrentSession().delete(theater);
	}

	@Override
	public void updateTheater(Theater theater) {
		getSessionFactory().getCurrentSession().update(theater);
	}

	@Override
	public Theater getTheaterById(int id) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Theater where id=?").setParameter(0, id).list();
		return (Theater) list.get(0);
	}

	@Override
	public List<Theater> getTheaterByName(String name) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Theater where name like lower('%" + name.toLowerCase() + "%')").list();
		return list;
	}

	@Override
	public List<Theater> getTheaters() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Theater").list();
		return list;
	}

	/*
	 * Getters and Setters
	 */

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

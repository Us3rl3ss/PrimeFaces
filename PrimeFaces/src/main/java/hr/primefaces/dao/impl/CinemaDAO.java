package hr.primefaces.dao.impl;

import hr.primefaces.dao.ICinemaDAO;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.Theater;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaDAO implements ICinemaDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public CinemaDAO() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addCinema(Cinema cinema) {
		getSessionFactory().getCurrentSession().save(cinema);
	}

	@Override
	public void deleteCinema(Cinema cinema) {
		getSessionFactory().getCurrentSession().delete(cinema);
	}

	@Override
	public void updateCinema(Cinema cinema) {
		getSessionFactory().getCurrentSession().update(cinema);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Cinema getCinemaById(int id) {
		List<Cinema> list = getSessionFactory().getCurrentSession()
				.createQuery("from Cinema where id=?").setParameter(0, id)
				.list();
		return (Cinema) list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getCinemaByTheater(Theater theater) {
		List<Cinema> list = getSessionFactory().getCurrentSession().createQuery("from Cinema where theater_id = :theater_id")
				.setParameter("theater_id", theater.getId()).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getCinemas() {
		List<Cinema> list = getSessionFactory().getCurrentSession()
				.createQuery("from Cinema").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getCinemaByName(String name) {
		List<Cinema> list = getSessionFactory().getCurrentSession().createQuery("from Cinema where name like lower('%" + name.toLowerCase() + "%')").list();
		return list;
	}

	@Override
	public Cinema getCinemaByTheaterAndName(Theater theater, String name) {
		Cinema cinema = (Cinema) getSessionFactory().getCurrentSession().createQuery("from Cinema where theater_id = :theater_id and name = :name")
				.setParameter("theater_id", theater.getId()).setParameter("name", name).uniqueResult();
		return cinema;
	}
}

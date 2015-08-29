package hr.primefaces.dao.impl;

import hr.primefaces.dao.ICinemaMovieDAO;
import hr.primefaces.model.CinemaMovie;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaMovieDAO implements ICinemaMovieDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public CinemaMovieDAO() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addCinemaMovie(CinemaMovie cinemaMovie) {
		getSessionFactory().getCurrentSession().save(cinemaMovie);
	}

	@Override
	public void deleteCinemaMovie(CinemaMovie cinemaMovie) {
		getSessionFactory().getCurrentSession().delete(cinemaMovie);
	}

	@Override
	public void updateCinemaMovie(CinemaMovie cinemaMovie) {
		getSessionFactory().getCurrentSession().update(cinemaMovie);
	}

	@Override
	public CinemaMovie getCinemaMovieById(int id) {
		List list = getSessionFactory().getCurrentSession().createQuery("from CinemaMovie where id=?").setParameter(0, id).list();
		return (CinemaMovie) list.get(0);
	}

	@Override
	public List<CinemaMovie> getCinemaMovies() {
		List list = getSessionFactory().getCurrentSession().createQuery("from CinemaMovie").list();
		return list;
	}

	@Override
	public List<CinemaMovie> getCinemaMovieByName(String name) {
		List list = getSessionFactory().getCurrentSession().createQuery("from CinemaMovie where name like lower('%" + name.toLowerCase() + "%')").list();
		return list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

package hr.primefaces.dao.impl;

import hr.primefaces.dao.ICinemaDAO;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.Theater;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaDAO implements ICinemaDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_CINEMA_BY_ID = "from Cinema where id = :cinema_id";
	private final String GET_CINEMA_BY_THEATER = "from Cinema where theater_id = :theater_id";
	private final String GET_CINEMAS = "from Cinema";
	private final String GET_CINEMA_BY_NAME = "from Cinema where name like :name";
	private final String GET_CINEMA_BY_THEATER_AND_NAME = "from Cinema where theater_id = :theater_id and name = :name";

	public CinemaDAO() {
	}

	@Override
	public void addCinema(final Cinema p_cinema) {
		getSessionFactory().getCurrentSession().save(p_cinema);
	}

	@Override
	public void deleteCinema(final Cinema p_cinema) {
		getSessionFactory().getCurrentSession().delete(p_cinema);
	}

	@Override
	public void updateCinema(final Cinema p_cinema) {
		getSessionFactory().getCurrentSession().update(p_cinema);
	}

	@Override
	public Cinema getCinemaById(final int p_id) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_CINEMA_BY_ID);
		query.setParameter("cinema_id", p_id);
		return (Cinema) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getCinemaByTheater(final Theater p_theater) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_CINEMA_BY_THEATER);
		query.setParameter("theater_id", p_theater.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getCinemas() {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_CINEMAS);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getCinemaByName(final String p_name) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_CINEMA_BY_NAME);
		query.setParameter("name", "%" + p_name.toLowerCase() + "%");
		return query.list();
	}

	@Override
	public Cinema getCinemaByTheaterAndName(final Theater p_theater, final String p_name) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_CINEMA_BY_THEATER_AND_NAME);
		query.setParameter("theater_id", p_theater.getId());
		query.setParameter("name", p_name);
		return (Cinema) query.uniqueResult();
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param p_sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(final SessionFactory p_sessionFactory) {
		this.sessionFactory = p_sessionFactory;
	}

}

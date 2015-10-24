package hr.primefaces.dao.impl;

import hr.primefaces.dao.ICinemaSeatsDAO;
import hr.primefaces.model.CinemaSeats;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaSeatsDAO implements ICinemaSeatsDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_CINEMASEATS_BY_ID = "from CinemaSeats where id = :cinemaseats_id";
	private final String GET_CINEMASEATS = "from CinemaSeats";
	private final String GET_CINEMASEATS_BY_NAME = "from CinemaSeats where name like :name";
	private final String GET_CINEMASEATS_BY_CINEMA_ID = "from CinemaSeats where cinema_id = :cinema_id";

	public CinemaSeatsDAO() {
	}

	@Override
	public void addCinemaSeats(final CinemaSeats p_cinemaSeats) {
		getSessionFactory().getCurrentSession().save(p_cinemaSeats);
	}

	@Override
	public void deleteCinemaSeats(final CinemaSeats p_cinemaSeats) {
		getSessionFactory().getCurrentSession().delete(p_cinemaSeats);
	}

	@Override
	public void updateCinemaSeats(final CinemaSeats p_cinemaSeats) {
		getSessionFactory().getCurrentSession().update(p_cinemaSeats);
	}

	@Override
	public CinemaSeats getCinemaSeatsById(final int p_id) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_CINEMASEATS_BY_ID);
		query.setParameter("cinemaseats_id", p_id);
		return (CinemaSeats) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CinemaSeats> getCinemaSeats() {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_CINEMASEATS);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CinemaSeats> getCinemaSeatsByName(final String p_name) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_CINEMASEATS_BY_NAME);
		query.setParameter("name", "%" + p_name.toLowerCase() + "%");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CinemaSeats> getCinemaSeatsByCinemaId(final int p_cinemaId) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_CINEMASEATS_BY_CINEMA_ID);
		query.setParameter("cinema_id", p_cinemaId);
		return query.list();
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

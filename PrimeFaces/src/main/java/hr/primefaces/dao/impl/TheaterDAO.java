package hr.primefaces.dao.impl;

import hr.primefaces.dao.ITheaterDAO;
import hr.primefaces.model.Theater;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TheaterDAO implements ITheaterDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_THEATER_BY_ID = "from Theater where id = :theater_id";
	private final String GET_THEATER_BY_NAME = "from Theater where name like :name";
	private final String GET_THEATERS = "from Theater";
	private final String GET_THEATER_BY_LAT_LNG = "from Theater where lat = :lat and lng = :lng";

	@Override
	public void addTheater(final Theater p_theater) {
		getSessionFactory().getCurrentSession().save(p_theater);
	}

	@Override
	public void deleteTheater(final Theater p_theater) {
		getSessionFactory().getCurrentSession().delete(p_theater);
	}

	@Override
	public void updateTheater(final Theater p_theater) {
		getSessionFactory().getCurrentSession().update(p_theater);
	}

	@Override
	public Theater getTheaterById(final int p_id) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_THEATER_BY_ID);
		query.setParameter("theater_id", p_id);
		return (Theater) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Theater> getTheaterByName(final String p_name) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_THEATER_BY_NAME);
		query.setParameter("name", "%" + p_name.toLowerCase() + "%");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Theater> getTheaters() {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_THEATERS);
		return query.list();
	}

	@Override
	public Theater getTheaterByLatLng(final Double p_lat, final Double p_lng) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_THEATER_BY_LAT_LNG);
		query.setParameter("lat", p_lat);
		query.setParameter("lng", p_lng);
		return (Theater) query.uniqueResult();
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

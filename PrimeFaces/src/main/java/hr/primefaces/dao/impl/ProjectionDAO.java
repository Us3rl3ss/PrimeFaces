package hr.primefaces.dao.impl;

import hr.primefaces.dao.IProjectionDAO;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectionDAO implements IProjectionDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_PROJECTION_BY_ID = "from Projection where id = :projection_id";
	private final String GET_PROJECTIONS = "from Projection";
	private final String GET_PROJECTIONS_BY_CINEMA = "from Projection projection where cinema_id = :cinema_id";
	private final String GET_PROJECTIONS_BY_THEATER = "from Projection projection join projection.cinema cinema join "
			+ "cinema.theater theater join projection.movie movie where theater.id = :theater_id";
	private final String GET_PROJECTIONS_FOR_RESERVATION = "from Projection where DATE_FORMAT(:datum,'%d.%m.%Y') = "
			+ "DATE_FORMAT(start_time,'%d.%m.%Y') and theater_id = :theater_id group by movie_id";
	private final String GET_DISTINCT_MOVIE_PROJECTIONS = "from Projection where theater_id = :theater_id and movie_id = :movie_id order by start_time asc";
	private final String GET_PROJECTION_BY_CINEMA_START_END = "from Projection where cinema_id = :cinema_id and start_time = :start and end_time = :end";
	private final String GET_PROJECTION_BY_CINEMA_BETWEEN_START_END = "from Projection where cinema_id = :cinema_id and "
			+ "start_time >= :start and end_time < :end";

	public ProjectionDAO() {
	}

	@Override
	public void addProjection(final Projection p_projection) {
		getSessionFactory().getCurrentSession().save(p_projection);
	}

	@Override
	public void deleteProjection(final Projection p_projection) {
		getSessionFactory().getCurrentSession().delete(p_projection);
	}

	@Override
	public void updateProjection(final Projection p_projection) {
		getSessionFactory().getCurrentSession().update(p_projection);
	}

	@Override
	public Projection getProjectionById(final int p_id) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTION_BY_ID);
		query.setParameter("projection_id", p_id);
		return (Projection) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjections() {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTIONS);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjectionsByCinema(final Cinema p_cinema) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTIONS_BY_CINEMA);
		query.setParameter("cinema_id", p_cinema.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjectionsByTheater(final Theater p_theater) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTIONS_BY_THEATER);
		query.setParameter("projection_id", p_theater.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjectionsForReservation(final Theater p_theater, final Date p_datumProjekcije) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTIONS_FOR_RESERVATION);
		query.setParameter("datum", p_datumProjekcije);
		query.setParameter("theater_id", p_theater.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getDistinctMovieProjections(final Projection p_projection) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_DISTINCT_MOVIE_PROJECTIONS);
		query.setParameter("theater_id", p_projection.getTheater().getId());
		query.setParameter("movie_id", p_projection.getMovie().getId());
		return query.list();
	}

	@Override
	public Projection getProjectionByCinemaStartEnd(final Cinema p_cinema, final Date p_start, final Date p_end) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTION_BY_CINEMA_START_END);
		query.setParameter("cinema_id", p_cinema.getId());
		query.setParameter("start", p_start);
		query.setParameter("end", p_end);
		return (Projection) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjectionByCinemaBetweenStartEnd(final Cinema p_cinema, final Date p_start, final Date p_end) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTION_BY_CINEMA_BETWEEN_START_END);
		query.setParameter("cinema_id", p_cinema.getId());
		query.setParameter("start", p_start);
		query.setParameter("end", p_end);
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

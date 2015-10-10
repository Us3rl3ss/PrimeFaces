package hr.primefaces.dao.impl;

import hr.primefaces.dao.IProjectionDAO;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectionDAO implements IProjectionDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public ProjectionDAO() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProjection(Projection projection) {
		getSessionFactory().getCurrentSession().save(projection);
	}

	@Override
	public void deleteProjection(Projection projection) {
		getSessionFactory().getCurrentSession().delete(projection);
	}

	@Override
	public void updateProjection(Projection projection) {
		getSessionFactory().getCurrentSession().update(projection);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Projection getProjectionById(int id) {
		List<Projection> list = getSessionFactory().getCurrentSession().createQuery("from Projection where id=?").setParameter(0, id).list();
		return (Projection) list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjections() {
		List<Projection> list = getSessionFactory().getCurrentSession().createQuery("from Projection").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjectionsByCinema(Cinema cinema) {

		String query = "from Projection projection where cinema_id = :cinema_id";

		List<Projection> list = getSessionFactory().getCurrentSession().createQuery(query).setParameter("cinema_id", cinema.getId()).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjectionsByTheater(Theater theater) {

		String query = "from Projection projection " + "join projection.cinema cinema " + "join cinema.theater theater " + "join projection.movie movie "
				+ "where theater.id = ?";

		List<Projection> list = getSessionFactory().getCurrentSession().createQuery(query).setParameter(0, theater.getId()).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjectionsForReservation(Theater theater, Date datumProjekcije) {

		String query = "from Projection where DATE_FORMAT(:datum,'%d.%m.%Y') = DATE_FORMAT(start_time,'%d.%m.%Y') and theater_id = :theaterId group by movie_id";

		List<Projection> list = getSessionFactory().getCurrentSession().createQuery(query).setParameter("datum", datumProjekcije).setParameter("theaterId", theater.getId()).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getDistinctMovieProjections(Projection projection) {

		String query = "from Projection where " + "theater_id = :theaterId " + "and movie_id = :movieId "
				+ "order by start_time asc";

		List<Projection> list = getSessionFactory().getCurrentSession().createQuery(query).setParameter("theaterId", projection.getTheater().getId())
				.setParameter("movieId", projection.getMovie().getId()).list();
		return list;
	}

	@Override
	public Projection getProjectionByCinemaStartEnd(Cinema cinema, Date start, Date end) {

		String query = "from Projection where cinema_id = :cinema_id and start_time = :start and end_time = :end";

		return (Projection) getSessionFactory().getCurrentSession().createQuery(query).setParameter("cinema_id", cinema.getId()).setParameter("start", start)
				.setParameter("end", end).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projection> getProjectionByCinemaBetweenStartEnd(Cinema cinema, Date start, Date end) {

		String query = "from Projection where cinema_id = :cinema_id and start_time >= :start and end_time < :end";

		return getSessionFactory().getCurrentSession().createQuery(query).setParameter("cinema_id", cinema.getId()).setParameter("start", start)
				.setParameter("end", end).list();
	}

}

package hr.primefaces.dao.impl;

import hr.primefaces.dao.IProjectionDAO;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;

import java.io.Serializable;
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

	@Override
	public Projection getProjectionById(int id) {
		List list = getSessionFactory().getCurrentSession().createQuery("from Projection where id=?").setParameter(0, id).list();
		return (Projection) list.get(0);
	}

	@Override
	public List<Projection> getProjections() {
		List list = getSessionFactory().getCurrentSession().createQuery("from Projection").list();
		return list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public List<Projection> getProjectionsByTheater(Theater theater) {

		String query = "from Projection projection " + "join projection.cinema cinema " + "join cinema.theater theater " + "join projection.movie movie "
				+ "where theater.id = ?";

		List list = getSessionFactory().getCurrentSession().createQuery(query).setParameter(0, theater.getId()).list();
		return list;
	}

	@Override
	public List<Projection> getProjectionsForReservation(Theater theater) {

		String query = "from Projection where current_timestamp < date and theater_id = :theaterId group by movie_id";

		List list = getSessionFactory().getCurrentSession().createQuery(query).setParameter("theaterId", theater.getId()).list();
		return list;
	}

	@Override
	public List<Projection> getDistinctMovieProjections(Projection projection) {

		String query = "from Projection where " + "theater_id = :theaterId " + "and movie_id = :movieId " + "and current_timestamp < date "
				+ "order by date desc";

		List list = getSessionFactory().getCurrentSession().createQuery(query).setParameter("theaterId", projection.getTheater().getId())
				.setParameter("movieId", projection.getMovie().getId()).list();
		return list;
	}

}

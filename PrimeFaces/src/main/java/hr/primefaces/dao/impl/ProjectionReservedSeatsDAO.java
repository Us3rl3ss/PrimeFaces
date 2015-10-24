package hr.primefaces.dao.impl;

import hr.primefaces.dao.IProjectionReservedSeatsDAO;
import hr.primefaces.model.Projection;
import hr.primefaces.model.ProjectionReservedSeats;
import hr.primefaces.model.User;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectionReservedSeatsDAO implements IProjectionReservedSeatsDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_PROJECTIONRESERVEDSEATS_BY_ID = "from ProjectionReservedSeats where id = :prs_id";
	private final String GET_PROJECTIONRESERVEDSEATS = "from ProjectionReservedSeats";
	private final String GET_PROJECTIONRESERVEDSEATS_BY_NAME = "from ProjectionReservedSeats where name like :name";
	private final String GET_PROJECTIONRESERVEDSEATS_BY_PROJECTION = "from ProjectionReservedSeats prs where projection_id = :projection_id";
	private final String GET_PROJECTIONRESERVEDSEATS_BY_PROJECTION_AND_USER = "from ProjectionReservedSeats prs where projection_id = "
			+ ":projection_id and user_id = :user_id";

	public ProjectionReservedSeatsDAO() {
	}

	@Override
	public void addProjectionReservedSeats(final ProjectionReservedSeats p_projectionReservedSeats) {
		getSessionFactory().getCurrentSession().save(p_projectionReservedSeats);
	}

	@Override
	public void deleteProjectionReservedSeats(final ProjectionReservedSeats p_projectionReservedSeats) {
		getSessionFactory().getCurrentSession().delete(p_projectionReservedSeats);
	}

	@Override
	public void updateProjectionReservedSeats(final ProjectionReservedSeats p_projectionReservedSeats) {
		getSessionFactory().getCurrentSession().update(p_projectionReservedSeats);
	}

	@Override
	public ProjectionReservedSeats getProjectionReservedSeatsById(final int p_id) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTIONRESERVEDSEATS_BY_ID);
		query.setParameter("prs_id", p_id);
		return (ProjectionReservedSeats) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeats() {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTIONRESERVEDSEATS);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByName(final String p_name) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTIONRESERVEDSEATS_BY_NAME);
		query.setParameter("name", "%" + p_name.toLowerCase() + "%");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjection(final Projection p_projection) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTIONRESERVEDSEATS_BY_PROJECTION);
		query.setParameter("projection_id", p_projection.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjectionAndUser(final Projection p_projection, final User p_user) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_PROJECTIONRESERVEDSEATS_BY_PROJECTION_AND_USER);
		query.setParameter("projection_id", p_projection.getId());
		query.setParameter("user_id", p_user.getId());
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

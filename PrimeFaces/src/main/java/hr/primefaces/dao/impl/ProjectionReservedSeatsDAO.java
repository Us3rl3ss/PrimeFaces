package hr.primefaces.dao.impl;

import hr.primefaces.dao.IProjectionReservedSeatsDAO;
import hr.primefaces.model.Projection;
import hr.primefaces.model.ProjectionReservedSeats;
import hr.primefaces.model.User;

import java.util.List;

import org.hibernate.SessionFactory;

public class ProjectionReservedSeatsDAO implements IProjectionReservedSeatsDAO {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public ProjectionReservedSeatsDAO() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addProjectionReservedSeats(
			ProjectionReservedSeats projectionReservedSeats) {
		getSessionFactory().getCurrentSession().save(projectionReservedSeats);
	}

	@Override
	public void deleteProjectionReservedSeats(
			ProjectionReservedSeats projectionReservedSeats) {
		getSessionFactory().getCurrentSession().delete(projectionReservedSeats);
	}

	@Override
	public void updateProjectionReservedSeats(
			ProjectionReservedSeats projectionReservedSeats) {
		getSessionFactory().getCurrentSession().update(projectionReservedSeats);
	}

	@Override
	public ProjectionReservedSeats getProjectionReservedSeatsById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from ProjectionReservedSeats where id=?")
				.setParameter(0, id).list();
		return (ProjectionReservedSeats) list.get(0);
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeats() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from ProjectionReservedSeats").list();
		return list;
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByName(
			String name) {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from ProjectionReservedSeats where name like lower('%"
								+ name.toLowerCase() + "%')").list();
		return list;
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjection(
			Projection projection) {
		
		String query = "from ProjectionReservedSeats prs "
				+ "where projection_id = ?";
		
		
		List list = getSessionFactory().getCurrentSession()
				.createQuery(query).setParameter(0, projection.getId()).list();
		return list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjectionAndUser(Projection projection, User user) {
		
		String query = "from ProjectionReservedSeats prs "
				+ "where projection_id = :projectionId and user_id = :userId";
		
		
		List list = getSessionFactory().getCurrentSession()
				.createQuery(query)
				.setParameter("projectionId", projection.getId())
				.setParameter("userId", user.getId())
				.list();
		return list;
	}
	
}

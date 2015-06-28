package hr.primefaces.service.impl;

import hr.primefaces.dao.IProjectionReservedSeatsDAO;
import hr.primefaces.model.Projection;
import hr.primefaces.model.ProjectionReservedSeats;
import hr.primefaces.model.User;
import hr.primefaces.service.IProjectionReservedSeatsService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "ProjectionReservedSeatsService")
@ApplicationScoped
@Transactional(readOnly = true)
public class ProjectionReservedSeatsService implements
		IProjectionReservedSeatsService, Serializable {

	private static final long serialVersionUID = 1L;

	IProjectionReservedSeatsDAO projectionReservedSeatsDAO;
	List<ProjectionReservedSeats> projectionReservedSeatsList;

	public ProjectionReservedSeatsService() {
	}

	public List<ProjectionReservedSeats> getProjectionReservedSeatsList() {
		return projectionReservedSeatsList;
	}

	public void setProjectionReservedSeatsList(
			List<ProjectionReservedSeats> projectionReservedSeatsList) {
		this.projectionReservedSeatsList = projectionReservedSeatsList;
	}

	@Transactional(readOnly = false)
	@Override
	public void addProjectionReservedSeats(
			ProjectionReservedSeats projectionReservedSeats) {
		getProjectionReservedSeatsDAO().addProjectionReservedSeats(
				projectionReservedSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteProjectionReservedSeats(
			ProjectionReservedSeats projectionReservedSeats) {
		getProjectionReservedSeatsDAO().deleteProjectionReservedSeats(
				projectionReservedSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateProjectionReservedSeats(
			ProjectionReservedSeats projectionReservedSeats) {
		getProjectionReservedSeatsDAO().updateProjectionReservedSeats(
				projectionReservedSeats);
	}

	@Override
	public ProjectionReservedSeats getProjectionReservedSeatsById(int id) {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeatsById(
				id);
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeats() {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeats();
	}

	public IProjectionReservedSeatsDAO getProjectionReservedSeatsDAO() {
		return projectionReservedSeatsDAO;
	}

	public void setProjectionReservedSeatsDAO(
			IProjectionReservedSeatsDAO projectionReservedSeatsDAO) {
		this.projectionReservedSeatsDAO = projectionReservedSeatsDAO;
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByName(
			String name) {
		return getProjectionReservedSeatsDAO()
				.getProjectionReservedSeatsByName(name);
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjection(
			Projection projection) {
		return getProjectionReservedSeatsDAO()
				.getProjectionReservedSeatsByProjection(projection);
	}
	
	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjectionAndUser(Projection projection, User user) {
		return getProjectionReservedSeatsDAO()
				.getProjectionReservedSeatsByProjectionAndUser(projection, user);
	}

}

package hr.primefaces.service.impl;

import hr.primefaces.dao.IProjectionDAO;
import hr.primefaces.dao.IProjectionReservedSeatsDAO;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.Projection;
import hr.primefaces.model.ProjectionReservedSeats;
import hr.primefaces.model.Theater;
import hr.primefaces.model.User;
import hr.primefaces.service.IProjectionService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "ProjectionService")
@ApplicationScoped
@Transactional(readOnly = true)
public class ProjectionService implements IProjectionService, Serializable {

	private static final long serialVersionUID = 1L;

	IProjectionDAO projectionDAO;
	IProjectionReservedSeatsDAO projectionReservedSeatsDAO;

	public ProjectionService() {
	}

	/**
	 * ################# PROJECTION #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addProjection(Projection projection) {
		getProjectionDAO().addProjection(projection);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteProjection(Projection projection) {
		getProjectionDAO().deleteProjection(projection);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateProjection(Projection projection) {
		getProjectionDAO().updateProjection(projection);
	}

	@Override
	public Projection getProjectionById(int id) {
		return getProjectionDAO().getProjectionById(id);
	}

	@Override
	public List<Projection> getProjections() {
		return getProjectionDAO().getProjections();
	}

	@Override
	public Projection getProjectionByCinemaStartEnd(Cinema cinema, Date start, Date end) {
		return getProjectionDAO().getProjectionByCinemaStartEnd(cinema, start, end);
	}

	@Override
	public List<Projection> getProjectionByCinemaBetweenStartEnd(Cinema cinema, Date start, Date end) {
		return getProjectionDAO().getProjectionByCinemaBetweenStartEnd(cinema, start, end);
	}

	public List<Projection> getProjectionsByTheater(Theater theater) {
		return getProjectionDAO().getProjectionsByTheater(theater);
	}

	public List<Projection> getProjectionsByCinema(Cinema cinema) {
		return getProjectionDAO().getProjectionsByCinema(cinema);
	}

	public List<Projection> getProjectionsForReservation(Theater theater, Date datumProjekcije) {
		return getProjectionDAO().getProjectionsForReservation(theater, datumProjekcije);
	}

	public List<Projection> getDistinctMovieProjections(Projection projection) {
		return getProjectionDAO().getDistinctMovieProjections(projection);
	}

	/**
	 * ################# END OF - PROJECTION #################
	 */

	/**
	 * ################# PROJECTION RESERVED SEATS #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addProjectionReservedSeats(ProjectionReservedSeats projectionReservedSeats) {
		getProjectionReservedSeatsDAO().addProjectionReservedSeats(projectionReservedSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteProjectionReservedSeats(ProjectionReservedSeats projectionReservedSeats) {
		getProjectionReservedSeatsDAO().deleteProjectionReservedSeats(projectionReservedSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateProjectionReservedSeats(ProjectionReservedSeats projectionReservedSeats) {
		getProjectionReservedSeatsDAO().updateProjectionReservedSeats(projectionReservedSeats);
	}

	@Override
	public ProjectionReservedSeats getProjectionReservedSeatsById(int id) {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeatsById(id);
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeats() {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeats();
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByName(String name) {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeatsByName(name);
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjection(Projection projection) {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeatsByProjection(projection);
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjectionAndUser(Projection projection, User user) {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeatsByProjectionAndUser(projection, user);
	}

	/**
	 * ################# END OF - PROJECTION RESERVED SEATS #################
	 */

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the projectionDAO
	 */
	public IProjectionDAO getProjectionDAO() {
		return projectionDAO;
	}

	/**
	 * @return the projectionReservedSeatsDAO
	 */
	public IProjectionReservedSeatsDAO getProjectionReservedSeatsDAO() {
		return projectionReservedSeatsDAO;
	}

	/**
	 * @param projectionDAO
	 *            the projectionDAO to set
	 */
	public void setProjectionDAO(IProjectionDAO projectionDAO) {
		this.projectionDAO = projectionDAO;
	}

	/**
	 * @param projectionReservedSeatsDAO
	 *            the projectionReservedSeatsDAO to set
	 */
	public void setProjectionReservedSeatsDAO(IProjectionReservedSeatsDAO projectionReservedSeatsDAO) {
		this.projectionReservedSeatsDAO = projectionReservedSeatsDAO;
	}

}

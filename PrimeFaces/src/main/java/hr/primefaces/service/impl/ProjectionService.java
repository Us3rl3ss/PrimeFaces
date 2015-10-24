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

	private IProjectionDAO projectionDAO;
	private IProjectionReservedSeatsDAO projectionReservedSeatsDAO;

	public ProjectionService() {
	}

	/**
	 * ################# PROJECTION #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addProjection(final Projection p_projection) {
		getProjectionDAO().addProjection(p_projection);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteProjection(final Projection p_projection) {
		getProjectionDAO().deleteProjection(p_projection);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateProjection(final Projection p_projection) {
		getProjectionDAO().updateProjection(p_projection);
	}

	@Override
	public Projection getProjectionById(final int p_id) {
		return getProjectionDAO().getProjectionById(p_id);
	}

	@Override
	public List<Projection> getProjections() {
		return getProjectionDAO().getProjections();
	}

	@Override
	public Projection getProjectionByCinemaStartEnd(final Cinema p_cinema, final Date p_start, final Date p_end) {
		return getProjectionDAO().getProjectionByCinemaStartEnd(p_cinema, p_start, p_end);
	}

	@Override
	public List<Projection> getProjectionByCinemaBetweenStartEnd(final Cinema p_cinema, final Date p_start, final Date p_end) {
		return getProjectionDAO().getProjectionByCinemaBetweenStartEnd(p_cinema, p_start, p_end);
	}

	public List<Projection> getProjectionsByTheater(final Theater p_theater) {
		return getProjectionDAO().getProjectionsByTheater(p_theater);
	}

	public List<Projection> getProjectionsByCinema(final Cinema p_cinema) {
		return getProjectionDAO().getProjectionsByCinema(p_cinema);
	}

	public List<Projection> getProjectionsForReservation(final Theater p_theater, final Date p_datumProjekcije) {
		return getProjectionDAO().getProjectionsForReservation(p_theater, p_datumProjekcije);
	}

	public List<Projection> getDistinctMovieProjections(final Projection p_projection) {
		return getProjectionDAO().getDistinctMovieProjections(p_projection);
	}

	/**
	 * ################# PROJECTION RESERVED SEATS #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addProjectionReservedSeats(final ProjectionReservedSeats p_projectionReservedSeats) {
		getProjectionReservedSeatsDAO().addProjectionReservedSeats(p_projectionReservedSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteProjectionReservedSeats(final ProjectionReservedSeats p_projectionReservedSeats) {
		getProjectionReservedSeatsDAO().deleteProjectionReservedSeats(p_projectionReservedSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateProjectionReservedSeats(final ProjectionReservedSeats p_projectionReservedSeats) {
		getProjectionReservedSeatsDAO().updateProjectionReservedSeats(p_projectionReservedSeats);
	}

	@Override
	public ProjectionReservedSeats getProjectionReservedSeatsById(final int p_id) {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeatsById(p_id);
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeats() {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeats();
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByName(final String p_name) {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeatsByName(p_name);
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjection(final Projection p_projection) {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeatsByProjection(p_projection);
	}

	@Override
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjectionAndUser(final Projection p_projection, final User p_user) {
		return getProjectionReservedSeatsDAO().getProjectionReservedSeatsByProjectionAndUser(p_projection, p_user);
	}

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
	 * @param p_projectionDAO
	 *            the projectionDAO to set
	 */
	public void setProjectionDAO(final IProjectionDAO p_projectionDAO) {
		this.projectionDAO = p_projectionDAO;
	}

	/**
	 * @param p_projectionReservedSeatsDAO
	 *            the projectionReservedSeatsDAO to set
	 */
	public void setProjectionReservedSeatsDAO(final IProjectionReservedSeatsDAO p_projectionReservedSeatsDAO) {
		this.projectionReservedSeatsDAO = p_projectionReservedSeatsDAO;
	}

}

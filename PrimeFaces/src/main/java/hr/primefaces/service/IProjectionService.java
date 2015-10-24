package hr.primefaces.service;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Projection;
import hr.primefaces.model.ProjectionReservedSeats;
import hr.primefaces.model.Theater;
import hr.primefaces.model.User;

import java.util.Date;
import java.util.List;

public interface IProjectionService {

	/**
	 * ################# PROJECTION #################
	 */

	void addProjection(Projection p_projection);

	void updateProjection(Projection p_projection);

	void deleteProjection(Projection p_projection);

	Projection getProjectionById(int p_id);

	List<Projection> getProjections();

	List<Projection> getProjectionsByTheater(Theater p_theater);

	List<Projection> getProjectionsByCinema(Cinema p_cinema);

	List<Projection> getProjectionsForReservation(Theater p_theater, Date p_datumProjekcije);

	List<Projection> getDistinctMovieProjections(Projection p_projection);

	Projection getProjectionByCinemaStartEnd(Cinema p_cinema, Date p_start, Date p_end);

	List<Projection> getProjectionByCinemaBetweenStartEnd(Cinema p_cinema, Date p_start, Date p_end);

	/**
	 * ################# PROJECTION RESERVED SEATS #################
	 */

	void addProjectionReservedSeats(ProjectionReservedSeats p_projectionReservedSeats);

	void updateProjectionReservedSeats(ProjectionReservedSeats p_projectionReservedSeats);

	void deleteProjectionReservedSeats(ProjectionReservedSeats p_projectionReservedSeats);

	ProjectionReservedSeats getProjectionReservedSeatsById(int p_id);

	List<ProjectionReservedSeats> getProjectionReservedSeatsByName(String p_name);

	List<ProjectionReservedSeats> getProjectionReservedSeats();

	List<ProjectionReservedSeats> getProjectionReservedSeatsByProjection(Projection p_projection);

	List<ProjectionReservedSeats> getProjectionReservedSeatsByProjectionAndUser(Projection p_projection, User p_user);

}

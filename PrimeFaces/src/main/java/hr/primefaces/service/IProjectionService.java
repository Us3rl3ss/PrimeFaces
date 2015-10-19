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

	public void addProjection(Projection projection);

	public void updateProjection(Projection projection);

	public void deleteProjection(Projection projection);

	public Projection getProjectionById(int id);

	public List<Projection> getProjections();

	public List<Projection> getProjectionsByTheater(Theater theater);

	public List<Projection> getProjectionsByCinema(Cinema cinema);

	public List<Projection> getProjectionsForReservation(Theater theater, Date datumProjekcije);

	public List<Projection> getDistinctMovieProjections(Projection projection);

	public Projection getProjectionByCinemaStartEnd(Cinema cinema, Date start, Date end);

	public List<Projection> getProjectionByCinemaBetweenStartEnd(Cinema cinema, Date start, Date end);

	/**
	 * ################# END OF - PROJECTION #################
	 */

	/**
	 * ################# PROJECTION RESERVED SEATS #################
	 */

	public void addProjectionReservedSeats(ProjectionReservedSeats projectionReservedSeats);

	public void updateProjectionReservedSeats(ProjectionReservedSeats projectionReservedSeats);

	public void deleteProjectionReservedSeats(ProjectionReservedSeats projectionReservedSeats);

	public ProjectionReservedSeats getProjectionReservedSeatsById(int id);

	public List<ProjectionReservedSeats> getProjectionReservedSeatsByName(String name);

	public List<ProjectionReservedSeats> getProjectionReservedSeats();

	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjection(Projection projection);

	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjectionAndUser(Projection projection, User user);

	/**
	 * ################# END OF - PROJECTION RESERVED SEATS #################
	 */
}

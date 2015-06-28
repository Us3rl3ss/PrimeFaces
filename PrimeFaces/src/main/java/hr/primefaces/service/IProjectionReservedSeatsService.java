package hr.primefaces.service;

import hr.primefaces.model.Projection;
import hr.primefaces.model.ProjectionReservedSeats;
import hr.primefaces.model.User;

import java.util.List;

public interface IProjectionReservedSeatsService {

	public void addProjectionReservedSeats(ProjectionReservedSeats projectionReservedSeats);

	public void updateProjectionReservedSeats(ProjectionReservedSeats projectionReservedSeats);

	public void deleteProjectionReservedSeats(ProjectionReservedSeats projectionReservedSeats);

	public ProjectionReservedSeats getProjectionReservedSeatsById(int id);
	
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByName(String name);

	public List<ProjectionReservedSeats> getProjectionReservedSeats();
	
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjection(Projection projection);
	
	public List<ProjectionReservedSeats> getProjectionReservedSeatsByProjectionAndUser(Projection projection, User user);
}

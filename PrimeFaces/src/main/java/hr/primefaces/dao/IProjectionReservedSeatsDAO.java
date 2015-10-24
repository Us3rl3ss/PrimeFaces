package hr.primefaces.dao;

import hr.primefaces.model.Projection;
import hr.primefaces.model.ProjectionReservedSeats;
import hr.primefaces.model.User;

import java.util.List;

public interface IProjectionReservedSeatsDAO {

	void addProjectionReservedSeats(ProjectionReservedSeats p_projectionReservedSeats);

	void updateProjectionReservedSeats(ProjectionReservedSeats p_projectionReservedSeats);

	void deleteProjectionReservedSeats(ProjectionReservedSeats p_projectionReservedSeats);

	ProjectionReservedSeats getProjectionReservedSeatsById(int p_id);

	List<ProjectionReservedSeats> getProjectionReservedSeatsByName(String p_name);

	List<ProjectionReservedSeats> getProjectionReservedSeats();

	List<ProjectionReservedSeats> getProjectionReservedSeatsByProjection(Projection p_projection);

	List<ProjectionReservedSeats> getProjectionReservedSeatsByProjectionAndUser(Projection p_projection, User p_user);

}

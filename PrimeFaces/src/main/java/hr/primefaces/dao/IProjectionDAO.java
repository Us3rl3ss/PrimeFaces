package hr.primefaces.dao;

import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;

import java.util.List;

public interface IProjectionDAO {

	public void addProjection(Projection projection);

	public void updateProjection(Projection projection);

	public void deleteProjection(Projection projection);

	public Projection getProjectionById(int id);

	public List<Projection> getProjections();
	
	public List<Projection> getProjectionsByTheater(Theater theater);
	
	public List<Projection> getProjectionsForReservation(Theater theater);
	
	public List<Projection> getDistinctMovieProjections(Projection projection);
}

package hr.primefaces.service;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;

import java.util.Date;
import java.util.List;

public interface IProjectionService {

	public void addProjection(Projection projection);

	public void updateProjection(Projection projection);

	public void deleteProjection(Projection projection);

	public Projection getProjectionById(int id);

	public List<Projection> getProjections();
	
	public List<Projection> getProjectionsByTheater(Theater theater);
	
	public List<Projection> getProjectionsByCinema(Cinema cinema);
	
	public List<Projection> getProjectionsForReservation(Theater theater);

	public List<Projection> getDistinctMovieProjections(Projection projection);
	
	public Projection getProjectionByCinemaStartEnd(Cinema cinema, Date start, Date end);
	
	public List<Projection> getProjectionByCinemaBetweenStartEnd(Cinema cinema, Date start, Date end);
}

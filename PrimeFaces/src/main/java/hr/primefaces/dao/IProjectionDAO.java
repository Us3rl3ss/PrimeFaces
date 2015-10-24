package hr.primefaces.dao;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;

import java.util.Date;
import java.util.List;

public interface IProjectionDAO {

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

}

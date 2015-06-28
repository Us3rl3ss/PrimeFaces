package hr.primefaces.dao;

import hr.primefaces.model.CinemaMovie;

import java.util.List;

public interface ICinemaMovieDAO {

	public void addCinemaMovie(CinemaMovie cinemaCinemaMovie);

	public void updateCinemaMovie(CinemaMovie cinemaCinemaMovie);

	public void deleteCinemaMovie(CinemaMovie cinemaCinemaMovie);

	public CinemaMovie getCinemaMovieById(int id);

	public List<CinemaMovie> getCinemaMovieByName(String name);
	
	public List<CinemaMovie> getCinemaMovies();
}

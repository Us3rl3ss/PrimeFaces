package hr.primefaces.service;

import hr.primefaces.model.CinemaMovie;

import java.util.List;

public interface ICinemaMovieService {

	public void addCinemaMovie(CinemaMovie cinemaMovie);

	public void updateCinemaMovie(CinemaMovie cinemaMovie);

	public void deleteCinemaMovie(CinemaMovie cinemaMovie);

	public CinemaMovie getCinemaMovieById(int id);

	public List<CinemaMovie> getCinemaMovieByName(String name);

	public List<CinemaMovie> getCinemaMovies();
}

package hr.primefaces.service;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;

import java.util.List;

public interface IMovieService {

	public void addMovie(Movie movie);

	public void updateMovie(Movie movie);

	public void deleteMovie(Movie movie);

	public Movie getMovieById(int id);
	
	public List<Movie> getMovieByName(String name);

	public List<Movie> getMovies();

	public List<Actor> getAllMovieActors(Movie movie);
	
	public List<Genre> getAllMovieGenres(Movie movie);
}

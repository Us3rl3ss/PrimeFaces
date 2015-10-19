package hr.primefaces.service;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;

import java.util.List;

public interface IMovieService {
	
	/**
	 * ################# MOVIE #################
	 */

	public void addMovie(Movie movie);

	public void updateMovie(Movie movie);

	public void deleteMovie(Movie movie);

	public Movie getMovieById(int id);
	
	public List<Movie> getMovieByName(String name);

	public List<Movie> getMovies();

	public List<Actor> getAllMovieActors(Movie movie);
	
	public List<Genre> getAllMovieGenres(Movie movie);
	
	/**
	 * ################# END OF - MOVIE #################
	 */
	
	/**
	 * ################# ACTOR #################
	 */
	
	public void addActor(Actor actor);
	
	public void updateActor(Actor actor);

	public void deleteActor(Actor actor);
	
	public Actor getActorById(int id);
	
	public List<Actor> getActorByName(String name);
	
	public List<Actor> getActors();
	
	/**
	 * ################# END OF - ACTOR #################
	 */
	
	/**
	 * ################# GENRE #################
	 */
	
	public void addGenre(Genre genre);

	public void updateGenre(Genre genre);

	public void deleteGenre(Genre genre);

	public Genre getGenreById(int id);

	public List<Genre> getGenreByName(String name);

	public List<Genre> getGenres();
	
	/**
	 * ################# END OF - GENRE #################
	 */
}

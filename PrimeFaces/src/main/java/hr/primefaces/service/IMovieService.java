package hr.primefaces.service;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;

import java.util.List;

public interface IMovieService {

	/**
	 * ################# MOVIE #################
	 */

	void addMovie(Movie p_movie);

	void updateMovie(Movie p_movie);

	void deleteMovie(Movie p_movie);

	Movie getMovieById(int p_id);

	List<Movie> getMovieByName(String p_name);

	List<Movie> getMovies();

	List<Actor> getAllMovieActors(Movie p_movie);

	List<Genre> getAllMovieGenres(Movie p_movie);

	Movie getMovieByImdbId(String p_imdbId);

	/**
	 * ################# ACTOR #################
	 */

	void addActor(Actor p_actor);

	void updateActor(Actor p_actor);

	void deleteActor(Actor p_actor);

	Actor getActorById(int p_id);

	List<Actor> getActorByName(String p_name);

	List<Actor> getActors();

	/**
	 * ################# GENRE #################
	 */

	void addGenre(Genre p_genre);

	void updateGenre(Genre p_genre);

	void deleteGenre(Genre p_genre);

	Genre getGenreById(int p_id);

	List<Genre> getGenreByName(String p_name);

	List<Genre> getGenres();

}

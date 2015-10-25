package hr.primefaces.dao;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;

import java.util.List;

public interface IMovieDAO {

	void addMovie(Movie p_movie);

	void updateMovie(Movie p_movie);

	void deleteMovie(Movie p_movie);

	Movie getMovieById(int p_id);

	List<Movie> getMovieByName(String p_name);

	List<Movie> getMovies();

	List<Actor> getAllMovieActors(Movie p_movie);

	List<Genre> getAllMovieGenres(Movie p_movie);

	Movie getMovieByImdbId(String p_imdbId);

}

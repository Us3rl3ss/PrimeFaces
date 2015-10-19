package hr.primefaces.service.impl;

import hr.primefaces.dao.IActorDAO;
import hr.primefaces.dao.IGenreDAO;
import hr.primefaces.dao.IMovieDAO;
import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.service.IMovieService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "MovieService")
@ApplicationScoped
@Transactional(readOnly = true)
public class MovieService implements IMovieService, Serializable {

	private static final long serialVersionUID = 1L;

	IMovieDAO movieDAO;
	IActorDAO actorDAO;
	IGenreDAO genreDAO;

	public MovieService() {
	}

	/**
	 * ################# MOVIE #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addMovie(Movie movie) {
		getMovieDAO().addMovie(movie);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteMovie(Movie movie) {
		getMovieDAO().deleteMovie(movie);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateMovie(Movie movie) {
		getMovieDAO().updateMovie(movie);
	}

	@Override
	public Movie getMovieById(int id) {
		return getMovieDAO().getMovieById(id);
	}

	@Override
	public List<Movie> getMovies() {
		return getMovieDAO().getMovies();
	}

	@Override
	public List<Movie> getMovieByName(String name) {
		return getMovieDAO().getMovieByName(name);
	}

	@Override
	public List<Actor> getAllMovieActors(Movie movie) {
		return getMovieDAO().getAllMovieActors(movie);
	}

	@Override
	public List<Genre> getAllMovieGenres(Movie movie) {
		return getMovieDAO().getAllMovieGenres(movie);
	}

	/**
	 * ################# END OF - MOVIE #################
	 */

	/**
	 * ################# ACTOR #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addActor(Actor actor) {
		getActorDAO().addActor(actor);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteActor(Actor actor) {
		getActorDAO().deleteActor(actor);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateActor(Actor actor) {
		getActorDAO().updateActor(actor);
	}

	@Override
	public Actor getActorById(int id) {
		return getActorDAO().getActorById(id);
	}

	@Override
	public List<Actor> getActorByName(String name) {
		return getActorDAO().getActorByName(name);
	}

	@Override
	public List<Actor> getActors() {
		return getActorDAO().getActors();
	}

	/**
	 * ################# END OF - ACTOR #################
	 */

	/**
	 * ################# GENRE #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addGenre(Genre genre) {
		getGenreDAO().addGenre(genre);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteGenre(Genre genre) {
		getGenreDAO().deleteGenre(genre);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateGenre(Genre genre) {
		getGenreDAO().updateGenre(genre);
	}

	@Override
	public Genre getGenreById(int id) {
		return getGenreDAO().getGenreById(id);
	}

	@Override
	public List<Genre> getGenres() {
		return getGenreDAO().getGenres();
	}

	@Override
	public List<Genre> getGenreByName(String name) {
		return getGenreDAO().getGenreByName(name);
	}

	/**
	 * ################# END OF - GENRE #################
	 */

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the movieDAO
	 */
	public IMovieDAO getMovieDAO() {
		return movieDAO;
	}

	/**
	 * @return the actorDAO
	 */
	public IActorDAO getActorDAO() {
		return actorDAO;
	}

	/**
	 * @return the genreDAO
	 */
	public IGenreDAO getGenreDAO() {
		return genreDAO;
	}

	/**
	 * @param movieDAO
	 *            the movieDAO to set
	 */
	public void setMovieDAO(IMovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	/**
	 * @param actorDAO
	 *            the actorDAO to set
	 */
	public void setActorDAO(IActorDAO actorDAO) {
		this.actorDAO = actorDAO;
	}

	/**
	 * @param genreDAO
	 *            the genreDAO to set
	 */
	public void setGenreDAO(IGenreDAO genreDAO) {
		this.genreDAO = genreDAO;
	}
}

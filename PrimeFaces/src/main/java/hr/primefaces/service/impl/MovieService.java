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

	private IMovieDAO movieDAO;
	private IActorDAO actorDAO;
	private IGenreDAO genreDAO;

	public MovieService() {
	}

	/**
	 * ################# MOVIE #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addMovie(final Movie p_movie) {
		getMovieDAO().addMovie(p_movie);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteMovie(final Movie p_movie) {
		getMovieDAO().deleteMovie(p_movie);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateMovie(final Movie p_movie) {
		getMovieDAO().updateMovie(p_movie);
	}

	@Override
	public Movie getMovieById(final int p_id) {
		return getMovieDAO().getMovieById(p_id);
	}

	@Override
	public List<Movie> getMovies() {
		return getMovieDAO().getMovies();
	}

	@Override
	public List<Movie> getMovieByName(final String p_name) {
		return getMovieDAO().getMovieByName(p_name);
	}

	@Override
	public List<Actor> getAllMovieActors(final Movie p_movie) {
		return getMovieDAO().getAllMovieActors(p_movie);
	}

	@Override
	public List<Genre> getAllMovieGenres(final Movie p_movie) {
		return getMovieDAO().getAllMovieGenres(p_movie);
	}

	@Override
	public Movie getMovieByImdbId(final String p_imdbId) {
		return getMovieDAO().getMovieByImdbId(p_imdbId);
	}

	/**
	 * ################# ACTOR #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addActor(final Actor p_actor) {
		getActorDAO().addActor(p_actor);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteActor(final Actor p_actor) {
		getActorDAO().deleteActor(p_actor);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateActor(final Actor p_actor) {
		getActorDAO().updateActor(p_actor);
	}

	@Override
	public Actor getActorById(final int p_id) {
		return getActorDAO().getActorById(p_id);
	}

	@Override
	public List<Actor> getActorByName(final String p_name) {
		return getActorDAO().getActorByName(p_name);
	}

	@Override
	public List<Actor> getActors() {
		return getActorDAO().getActors();
	}

	/**
	 * ################# GENRE #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addGenre(final Genre p_genre) {
		getGenreDAO().addGenre(p_genre);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteGenre(final Genre p_genre) {
		getGenreDAO().deleteGenre(p_genre);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateGenre(final Genre p_genre) {
		getGenreDAO().updateGenre(p_genre);
	}

	@Override
	public Genre getGenreById(final int p_id) {
		return getGenreDAO().getGenreById(p_id);
	}

	@Override
	public List<Genre> getGenres() {
		return getGenreDAO().getGenres();
	}

	@Override
	public List<Genre> getGenreByName(final String p_name) {
		return getGenreDAO().getGenreByName(p_name);
	}

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
	 * @param p_movieDAO
	 *            the movieDAO to set
	 */
	public void setMovieDAO(final IMovieDAO p_movieDAO) {
		this.movieDAO = p_movieDAO;
	}

	/**
	 * @param p_actorDAO
	 *            the actorDAO to set
	 */
	public void setActorDAO(final IActorDAO p_actorDAO) {
		this.actorDAO = p_actorDAO;
	}

	/**
	 * @param p_genreDAO
	 *            the genreDAO to set
	 */
	public void setGenreDAO(final IGenreDAO p_genreDAO) {
		this.genreDAO = p_genreDAO;
	}
}

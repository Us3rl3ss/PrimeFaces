package hr.primefaces.dao.impl;

import hr.primefaces.dao.IMovieDAO;
import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDAO implements IMovieDAO, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	private final String GET_MOVIE_BY_ID = "from Movie where id = :movie_id";
	private final String GET_MOVIES = "from Movie";
	private final String GET_MOVIE_BY_NAME = "from Movie where name like :name";
	private final String GET_ALL_MOVIE_ACTORS = "select actorList from Movie where movie_id = :movie_id";
	private final String GET_ALL_MOVIE_GENRES = "select genreList from Movie where movie_id = :movie_id";

	public MovieDAO() {
	}

	@Override
	public void addMovie(final Movie p_movie) {
		getSessionFactory().getCurrentSession().save(p_movie);
	}

	@Override
	public void deleteMovie(final Movie p_movie) {
		getSessionFactory().getCurrentSession().delete(p_movie);
	}

	@Override
	public void updateMovie(final Movie p_movie) {
		getSessionFactory().getCurrentSession().update(p_movie);
	}

	@Override
	public Movie getMovieById(final int p_id) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_MOVIE_BY_ID);
		query.setParameter("movie_id", p_id);
		return (Movie) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMovies() {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_MOVIES);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Movie> getMovieByName(final String p_name) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_MOVIE_BY_NAME);
		query.setParameter("name", "%" + p_name.toLowerCase() + "%");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Actor> getAllMovieActors(final Movie p_movie) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_ALL_MOVIE_ACTORS);
		query.setParameter("movie_id", p_movie.getId());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Genre> getAllMovieGenres(final Movie p_movie) {

		final Query query = getSessionFactory().getCurrentSession().createQuery(GET_ALL_MOVIE_GENRES);
		query.setParameter("movie_id", p_movie.getId());
		return query.list();
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param p_sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(final SessionFactory p_sessionFactory) {
		this.sessionFactory = p_sessionFactory;
	}

}

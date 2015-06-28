package hr.primefaces.dao.impl;

import hr.primefaces.dao.IMovieDAO;
import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;

import java.util.List;

import org.hibernate.SessionFactory;

public class MovieDAO implements IMovieDAO {

	private static final long serialVersionUID = 1L;

	private SessionFactory sessionFactory;

	public MovieDAO() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addMovie(Movie movie) {
		getSessionFactory().getCurrentSession().save(movie);
	}

	@Override
	public void deleteMovie(Movie movie) {
		getSessionFactory().getCurrentSession().delete(movie);
	}

	@Override
	public void updateMovie(Movie movie) {
		getSessionFactory().getCurrentSession().update(movie);
	}

	@Override
	public Movie getMovieById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Movie where id=?").setParameter(0, id)
				.list();
		return (Movie) list.get(0);
	}

	@Override
	public List<Movie> getMovies() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Movie").list();
		return list;
	}

	@Override
	public List<Movie> getMovieByName(String name) {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						"from Movie where name like lower('%"
								+ name.toLowerCase() + "%')").list();
		return list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public List<Actor> getAllMovieActors(Movie movie) {
		
		String query = "select actorList from Movie where movie_id = :movieId";
		
		List list = getSessionFactory().getCurrentSession()
				.createQuery(query)
				.setParameter("movieId", movie.getId())
				.list();
		return list;
	}
	
	@Override
	public List<Genre> getAllMovieGenres(Movie movie) {
		
		String query = "select genreList from Movie where movie_id = :movieId";
		
		List list = getSessionFactory().getCurrentSession()
				.createQuery(query)
				.setParameter("movieId", movie.getId())
				.list();
		return list;
	}

}

package hr.primefaces.service.impl;

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
	List<Movie> movieList;
	
	public MovieService() {}
	
	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

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

	public IMovieDAO getMovieDAO() {
		return movieDAO;
	}

	public void setMovieDAO(IMovieDAO movieDAO) {
		this.movieDAO = movieDAO;
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

}

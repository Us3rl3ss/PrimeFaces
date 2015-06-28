package hr.primefaces.bean;

import hr.primefaces.model.Movie;
import hr.primefaces.service.IMovieService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "getMovieMB")
@ViewScoped
public class GetMovieManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	private Movie movie = new Movie();

	private List<Movie> movieList;

	@PostConstruct
	public void init() {
		movieList = movieService.getMovies();
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

}
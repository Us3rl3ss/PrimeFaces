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
public class GetMovieView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	private List<Movie> movieList;

	@PostConstruct
	public void init() {

		setMovieList(getMovieService().getMovies());
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @return the movieList
	 */
	public List<Movie> getMovieList() {
		return movieList;
	}

	/**
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

	/**
	 * @param p_movieList the movieList to set
	 */
	public void setMovieList(final List<Movie> p_movieList) {
		this.movieList = p_movieList;
	}

}
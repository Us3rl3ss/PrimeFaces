package hr.primefaces.bean;

import hr.primefaces.model.Genre;
import hr.primefaces.service.IMovieService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "getGenreMB")
@ViewScoped
public class GetGenreView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	private Genre genre = new Genre();

	private List<Genre> genreList;

	@PostConstruct
	public void init() {
		genreList = movieService.getGenres();
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @param movieService
	 *            the movieService to set
	 */
	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

}
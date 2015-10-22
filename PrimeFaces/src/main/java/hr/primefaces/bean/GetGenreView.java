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
	private IMovieService movieService;

	private Genre genre;
	private List<Genre> genreList;

	@PostConstruct
	public void init() {

		setGenre(new Genre());
		setGenreList(getMovieService().getGenres());
	}

	public Genre getGenre() {
		return genre;
	}

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @return the genreList
	 */
	public List<Genre> getGenreList() {
		return genreList;
	}

	/**
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

	/**
	 * @param p_genre the genre to set
	 */
	public void setGenre(final Genre p_genre) {
		this.genre = p_genre;
	}

	/**
	 * @param p_genreList the genreList to set
	 */
	public void setGenreList(final List<Genre> p_genreList) {
		this.genreList = p_genreList;
	}

}
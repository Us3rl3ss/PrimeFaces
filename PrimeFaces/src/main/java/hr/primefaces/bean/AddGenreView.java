package hr.primefaces.bean;

import hr.primefaces.model.Genre;
import hr.primefaces.service.IMovieService;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.HibernateException;

@ManagedBean(name = "addGenreMB")
@ViewScoped
public class AddGenreView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	private Genre genre = new Genre();

	@PostConstruct
	public void init() {
	}

	/**
	 * spremi
	 */
	public void spremi() {

		try {
			genre.setCreated(new Date());
			movieService.addGenre(genre);
			genre = new Genre();
			MessageUtil.info("Podaci uspješno spremljeni!");
		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
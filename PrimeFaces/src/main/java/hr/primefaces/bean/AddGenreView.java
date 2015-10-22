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
	private IMovieService movieService;

	private Genre genre;

	@PostConstruct
	public void init() {

		setGenre(new Genre());
	}

	/**
	 * save
	 */
	public void save() {

		try {
			getGenre().setCreated(new Date());
			getMovieService().addGenre(getGenre());
			setGenre(new Genre());
			MessageUtil.info("Podaci uspješno spremljeni!");
		}
		catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
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
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
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

}
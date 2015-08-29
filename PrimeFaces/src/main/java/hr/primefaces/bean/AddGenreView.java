package hr.primefaces.bean;

import hr.primefaces.model.Genre;
import hr.primefaces.service.IGenreService;
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

	@ManagedProperty(value = "#{GenreService}")
	IGenreService genreService;

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
			genreService.addGenre(genre);
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

	public IGenreService getGenreService() {
		return genreService;
	}

	public void setGenreService(IGenreService genreService) {
		this.genreService = genreService;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
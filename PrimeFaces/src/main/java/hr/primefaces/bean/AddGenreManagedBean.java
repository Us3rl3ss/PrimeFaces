package hr.primefaces.bean;

import hr.primefaces.model.Genre;
import hr.primefaces.service.IGenreService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "addGenreMB")
@ViewScoped
public class AddGenreManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{GenreService}")
	IGenreService genreService;

	@ManagedProperty("#{dropDownMB}")
	private DropdownMenuManagedBean dropDownMB;

	private Genre genre = new Genre();

	private List<Genre> genreList;

	@PostConstruct
	public void init() {
	}

	public void spremi() {

		try {
			genre.setCreated(new Date());
			genreService.addGenre(genre);
			dropDownMB.reloadGenre();
		} catch (Exception ex) { // TODO ERROR HANDLING
			ex.printStackTrace();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	public DropdownMenuManagedBean getDropDownMB() {
		return dropDownMB;
	}

	public void setDropDownMB(DropdownMenuManagedBean dropDownMB) {
		this.dropDownMB = dropDownMB;
	}

}
package hr.primefaces.service.impl;

import hr.primefaces.dao.IGenreDAO;
import hr.primefaces.model.Genre;
import hr.primefaces.service.IGenreService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "GenreService")
@ApplicationScoped
@Transactional(readOnly = true)
public class GenreService implements IGenreService, Serializable {

	private static final long serialVersionUID = 1L;

	IGenreDAO genreDAO;
	List<Genre> genreList;

	public GenreService() {
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	@Transactional(readOnly = false)
	@Override
	public void addGenre(Genre genre) {
		getGenreDAO().addGenre(genre);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteGenre(Genre genre) {
		getGenreDAO().deleteGenre(genre);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateGenre(Genre genre) {
		getGenreDAO().updateGenre(genre);
	}

	@Override
	public Genre getGenreById(int id) {
		return getGenreDAO().getGenreById(id);
	}

	@Override
	public List<Genre> getGenres() {
		return getGenreDAO().getGenres();
	}

	public IGenreDAO getGenreDAO() {
		return genreDAO;
	}

	public void setGenreDAO(IGenreDAO genreDAO) {
		this.genreDAO = genreDAO;
	}

	@Override
	public List<Genre> getGenreByName(String name) {
		return getGenreDAO().getGenreByName(name);
	}

}

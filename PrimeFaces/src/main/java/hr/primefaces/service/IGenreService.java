package hr.primefaces.service;

import hr.primefaces.model.Genre;

import java.util.List;

public interface IGenreService {

	public void addGenre(Genre genre);

	public void updateGenre(Genre genre);

	public void deleteGenre(Genre genre);

	public Genre getGenreById(int id);

	public List<Genre> getGenreByName(String name);

	public List<Genre> getGenres();
}

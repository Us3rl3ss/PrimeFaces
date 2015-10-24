package hr.primefaces.dao;

import hr.primefaces.model.Genre;

import java.util.List;

public interface IGenreDAO {

	void addGenre(Genre p_genre);

	void updateGenre(Genre p_genre);

	void deleteGenre(Genre p_genre);

	Genre getGenreById(int p_id);

	List<Genre> getGenreByName(String p_name);

	List<Genre> getGenres();

}

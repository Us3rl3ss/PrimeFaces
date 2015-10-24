package hr.primefaces.dao;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Theater;

import java.util.List;

public interface ICinemaDAO {

	void addCinema(Cinema p_cinema);

	void updateCinema(Cinema p_cinema);

	void deleteCinema(Cinema p_cinema);

	Cinema getCinemaById(int p_id);

	List<Cinema> getCinemaByName(String p_name);

	List<Cinema> getCinemas();

	List<Cinema> getCinemaByTheater(Theater p_theater);

	Cinema getCinemaByTheaterAndName(Theater p_theater, String p_name);

}

package hr.primefaces.dao;


/*
 * Imports
 */

import hr.primefaces.model.Theater;

import java.util.List;

public interface ITheaterDAO {

	void addTheater(Theater p_theater);

	void updateTheater(Theater p_theater);

	void deleteTheater(Theater p_theater);

	Theater getTheaterById(int p_id);

	List<Theater> getTheaterByName(String p_username);

	List<Theater> getTheaters();

	Theater getTheaterByLatLng(Double p_lat, Double p_lng);

}

package hr.primefaces.service;

/*
 * Imports
 */

import hr.primefaces.model.Cinema;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Theater;

import java.util.List;

public interface ITheaterService {

	/**
	 * ################# THEATER #################
	 */

	void addTheater(Theater p_theater);

	void updateTheater(Theater p_theater);

	void deleteTheater(Theater p_theater);

	Theater getTheaterById(int p_id);

	List<Theater> getTheaterByName(String p_name);

	List<Theater> getTheaters();

	Theater getTheaterByLatLng(Double p_lat, Double p_lng);

	/**
	 * ################# CINEMA #################
	 */

	void addCinema(Cinema p_cinema);

	void updateCinema(Cinema p_cinema);

	void deleteCinema(Cinema p_cinema);

	Cinema getCinemaById(int p_id);

	List<Cinema> getCinemaByName(String p_name);

	List<Cinema> getCinemas();

	List<Cinema> getCinemaByTheater(Theater p_theater);

	Cinema getCinemaByTheaterAndName(Theater p_theater, String p_name);

	/**
	 * ################# CINEMA SEATS #################
	 */

	void addCinemaSeats(CinemaSeats p_cinemaSeats);

	void updateCinemaSeats(CinemaSeats p_cinemaSeats);

	void deleteCinemaSeats(CinemaSeats p_cinemaSeats);

	CinemaSeats getCinemaSeatsById(int p_id);

	List<CinemaSeats> getCinemaSeatsByName(String p_name);

	List<CinemaSeats> getCinemaSeats();

	List<CinemaSeats> getCinemaSeatsByCinemaId(int p_cinemaId);

}

package hr.primefaces.service;

/*
 * Imports
 */

import hr.primefaces.model.Cinema;
import hr.primefaces.model.CinemaMovie;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Theater;

import java.util.List;

public interface ITheaterService {

	/**
	 * ################# THEATER #################
	 */

	public void addTheater(Theater theater);

	public void updateTheater(Theater theater);

	public void deleteTheater(Theater theater);

	public Theater getTheaterById(int id);

	public List<Theater> getTheaterByName(String name);

	public List<Theater> getTheaters();

	public Theater getTheaterByLatLng(Double lat, Double lng);

	/**
	 * ################# END OF - THEATER #################
	 */

	/**
	 * ################# CINEMA #################
	 */

	public void addCinema(Cinema cinema);

	public void updateCinema(Cinema cinema);

	public void deleteCinema(Cinema cinema);

	public Cinema getCinemaById(int id);

	public List<Cinema> getCinemaByName(String name);

	public List<Cinema> getCinemas();

	public List<Cinema> getCinemaByTheater(Theater theater);

	public Cinema getCinemaByTheaterAndName(Theater theater, String name);

	/**
	 * ################# END OF - CINEMA #################
	 */

	/**
	 * ################# CINEMA MOVIE #################
	 */

	public void addCinemaMovie(CinemaMovie cinemaMovie);

	public void updateCinemaMovie(CinemaMovie cinemaMovie);

	public void deleteCinemaMovie(CinemaMovie cinemaMovie);

	public CinemaMovie getCinemaMovieById(int id);

	public List<CinemaMovie> getCinemaMovieByName(String name);

	public List<CinemaMovie> getCinemaMovies();

	/**
	 * ################# END OF - CINEMA MOVIE #################
	 */

	/**
	 * ################# CINEMA SEATS #################
	 */

	public void addCinemaSeats(CinemaSeats cinemaSeats);

	public void updateCinemaSeats(CinemaSeats cinemaSeats);

	public void deleteCinemaSeats(CinemaSeats cinemaSeats);

	public CinemaSeats getCinemaSeatsById(int id);

	public List<CinemaSeats> getCinemaSeatsByName(String name);

	public List<CinemaSeats> getCinemaSeats();

	public List<CinemaSeats> getCinemaSeatsByCinemaId(int cinemaId);

	/**
	 * ################# END OF - CINEMA SEATS #################
	 */
}

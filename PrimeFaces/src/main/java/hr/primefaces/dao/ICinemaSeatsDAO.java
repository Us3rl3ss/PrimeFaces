package hr.primefaces.dao;

import hr.primefaces.model.CinemaSeats;

import java.util.List;

public interface ICinemaSeatsDAO {

	public void addCinemaSeats(CinemaSeats cinemaSeats);

	public void updateCinemaSeats(CinemaSeats cinemaSeats);

	public void deleteCinemaSeats(CinemaSeats cinemaSeats);

	public CinemaSeats getCinemaSeatsById(int id);

	public List<CinemaSeats> getCinemaSeatsByName(String name);

	public List<CinemaSeats> getCinemaSeats();
	
	public List<CinemaSeats> getCinemaSeatsByCinemaId(int cinemaId);

}

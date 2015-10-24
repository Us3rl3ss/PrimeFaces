package hr.primefaces.dao;

import hr.primefaces.model.CinemaSeats;

import java.util.List;

public interface ICinemaSeatsDAO {

	void addCinemaSeats(CinemaSeats p_cinemaSeats);

	void updateCinemaSeats(CinemaSeats p_cinemaSeats);

	void deleteCinemaSeats(CinemaSeats p_cinemaSeats);

	CinemaSeats getCinemaSeatsById(int p_id);

	List<CinemaSeats> getCinemaSeatsByName(String p_name);

	List<CinemaSeats> getCinemaSeats();

	List<CinemaSeats> getCinemaSeatsByCinemaId(int p_cinemaId);

}

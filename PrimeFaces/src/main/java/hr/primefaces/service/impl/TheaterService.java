package hr.primefaces.service.impl;

import hr.primefaces.dao.ICinemaDAO;
import hr.primefaces.dao.ITheaterDAO;
import hr.primefaces.dao.impl.CinemaSeatsDAO;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ITheaterService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "TheaterService")
@ApplicationScoped
@Transactional(readOnly = true)
public class TheaterService implements ITheaterService, Serializable {

	private static final long serialVersionUID = 1L;

	ITheaterDAO theaterDAO;
	ICinemaDAO cinemaDAO;
	CinemaSeatsDAO cinemaSeatsDAO;
	
	/**
	 * ################# THEATER #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addTheater(Theater theater) {
		getTheaterDAO().addTheater(theater);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteTheater(Theater theater) {
		getTheaterDAO().deleteTheater(theater);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateTheater(Theater theater) {
		getTheaterDAO().updateTheater(theater);
	}

	@Override
	public Theater getTheaterById(int id) {
		return getTheaterDAO().getTheaterById(id);
	}

	@Override
	public List<Theater> getTheaters() {
		return getTheaterDAO().getTheaters();
	}

	@Override
	public List<Theater> getTheaterByName(String name) {
		return getTheaterDAO().getTheaterByName(name);
	}

	@Override
	public Theater getTheaterByLatLng(Double lat, Double lng) {
		return getTheaterDAO().getTheaterByLatLng(lat, lng);
	}
	
	/**
	 * ################# END OF - THEATER #################
	 */
	
	/**
	 * ################# CINEMA #################
	 */
	
	@Transactional(readOnly = false)
	@Override
	public void addCinema(Cinema cinema) {
		getCinemaDAO().addCinema(cinema);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteCinema(Cinema cinema) {
		getCinemaDAO().deleteCinema(cinema);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateCinema(Cinema cinema) {
		getCinemaDAO().updateCinema(cinema);
	}

	@Override
	public Cinema getCinemaById(int id) {
		return getCinemaDAO().getCinemaById(id);
	}

	@Override
	public List<Cinema> getCinemas() {
		return getCinemaDAO().getCinemas();
	}
	
	@Override
	public List<Cinema> getCinemaByTheater(Theater theater) {
		return getCinemaDAO().getCinemaByTheater(theater);
	}

	@Override
	public Cinema getCinemaByTheaterAndName(Theater theater, String name) {
		return getCinemaDAO().getCinemaByTheaterAndName(theater, name);
	}

	@Override
	public List<Cinema> getCinemaByName(String name) {
		return getCinemaDAO().getCinemaByName(name);
	}

	/**
	 * ################# END OF - CINEMA #################
	 */


	/**
	 * ################# CINEMA SEATS #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addCinemaSeats(CinemaSeats cinemaSeats) {
		getCinemaSeatsDAO().addCinemaSeats(cinemaSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteCinemaSeats(CinemaSeats cinemaSeats) {
		getCinemaSeatsDAO().deleteCinemaSeats(cinemaSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateCinemaSeats(CinemaSeats cinemaSeats) {
		getCinemaSeatsDAO().updateCinemaSeats(cinemaSeats);
	}

	@Override
	public CinemaSeats getCinemaSeatsById(int id) {
		return getCinemaSeatsDAO().getCinemaSeatsById(id);
	}

	@Override
	public List<CinemaSeats> getCinemaSeats() {
		return getCinemaSeatsDAO().getCinemaSeats();
	}

	@Override
	public List<CinemaSeats> getCinemaSeatsByName(String name) {
		return getCinemaSeatsDAO().getCinemaSeatsByName(name);
	}
	
	@Override
	public List<CinemaSeats> getCinemaSeatsByCinemaId(int cinemaId) {
		return getCinemaSeatsDAO().getCinemaSeatsByCinemaId(cinemaId);
	}
	
	/**
	 * ################# END OF - CINEMA SEATS #################
	 */
	
	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the theaterDAO
	 */
	public ITheaterDAO getTheaterDAO() {
		return theaterDAO;
	}

	/**
	 * @return the cinemaDAO
	 */
	public ICinemaDAO getCinemaDAO() {
		return cinemaDAO;
	}

	/**
	 * @return the cinemaSeatsDAO
	 */
	public CinemaSeatsDAO getCinemaSeatsDAO() {
		return cinemaSeatsDAO;
	}

	/**
	 * @param theaterDAO the theaterDAO to set
	 */
	public void setTheaterDAO(ITheaterDAO theaterDAO) {
		this.theaterDAO = theaterDAO;
	}

	/**
	 * @param cinemaDAO the cinemaDAO to set
	 */
	public void setCinemaDAO(ICinemaDAO cinemaDAO) {
		this.cinemaDAO = cinemaDAO;
	}

	/**
	 * @param cinemaSeatsDAO the cinemaSeatsDAO to set
	 */
	public void setCinemaSeatsDAO(CinemaSeatsDAO cinemaSeatsDAO) {
		this.cinemaSeatsDAO = cinemaSeatsDAO;
	}

}

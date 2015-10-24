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

	private ITheaterDAO theaterDAO;
	private ICinemaDAO cinemaDAO;
	private CinemaSeatsDAO cinemaSeatsDAO;

	/**
	 * ################# THEATER #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addTheater(final Theater p_theater) {
		getTheaterDAO().addTheater(p_theater);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteTheater(final Theater p_theater) {
		getTheaterDAO().deleteTheater(p_theater);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateTheater(final Theater p_theater) {
		getTheaterDAO().updateTheater(p_theater);
	}

	@Override
	public Theater getTheaterById(final int p_id) {
		return getTheaterDAO().getTheaterById(p_id);
	}

	@Override
	public List<Theater> getTheaters() {
		return getTheaterDAO().getTheaters();
	}

	@Override
	public List<Theater> getTheaterByName(final String p_name) {
		return getTheaterDAO().getTheaterByName(p_name);
	}

	@Override
	public Theater getTheaterByLatLng(final Double p_lat, final Double p_lng) {
		return getTheaterDAO().getTheaterByLatLng(p_lat, p_lng);
	}

	/**
	 * ################# CINEMA #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addCinema(final Cinema p_cinema) {
		getCinemaDAO().addCinema(p_cinema);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteCinema(final Cinema p_cinema) {
		getCinemaDAO().deleteCinema(p_cinema);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateCinema(final Cinema p_cinema) {
		getCinemaDAO().updateCinema(p_cinema);
	}

	@Override
	public Cinema getCinemaById(final int p_id) {
		return getCinemaDAO().getCinemaById(p_id);
	}

	@Override
	public List<Cinema> getCinemas() {
		return getCinemaDAO().getCinemas();
	}

	@Override
	public List<Cinema> getCinemaByTheater(final Theater p_theater) {
		return getCinemaDAO().getCinemaByTheater(p_theater);
	}

	@Override
	public Cinema getCinemaByTheaterAndName(final Theater p_theater, final String p_name) {
		return getCinemaDAO().getCinemaByTheaterAndName(p_theater, p_name);
	}

	@Override
	public List<Cinema> getCinemaByName(final String p_name) {
		return getCinemaDAO().getCinemaByName(p_name);
	}

	/**
	 * ################# CINEMA SEATS #################
	 */

	@Transactional(readOnly = false)
	@Override
	public void addCinemaSeats(final CinemaSeats p_cinemaSeats) {
		getCinemaSeatsDAO().addCinemaSeats(p_cinemaSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteCinemaSeats(final CinemaSeats p_cinemaSeats) {
		getCinemaSeatsDAO().deleteCinemaSeats(p_cinemaSeats);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateCinemaSeats(final CinemaSeats p_cinemaSeats) {
		getCinemaSeatsDAO().updateCinemaSeats(p_cinemaSeats);
	}

	@Override
	public CinemaSeats getCinemaSeatsById(final int p_id) {
		return getCinemaSeatsDAO().getCinemaSeatsById(p_id);
	}

	@Override
	public List<CinemaSeats> getCinemaSeats() {
		return getCinemaSeatsDAO().getCinemaSeats();
	}

	@Override
	public List<CinemaSeats> getCinemaSeatsByName(final String p_name) {
		return getCinemaSeatsDAO().getCinemaSeatsByName(p_name);
	}

	@Override
	public List<CinemaSeats> getCinemaSeatsByCinemaId(final int p_cinemaId) {
		return getCinemaSeatsDAO().getCinemaSeatsByCinemaId(p_cinemaId);
	}

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
	 * @param p_theaterDAO the theaterDAO to set
	 */
	public void setTheaterDAO(final ITheaterDAO p_theaterDAO) {
		this.theaterDAO = p_theaterDAO;
	}

	/**
	 * @param p_cinemaDAO the cinemaDAO to set
	 */
	public void setCinemaDAO(final ICinemaDAO p_cinemaDAO) {
		this.cinemaDAO = p_cinemaDAO;
	}

	/**
	 * @param p_cinemaSeatsDAO the cinemaSeatsDAO to set
	 */
	public void setCinemaSeatsDAO(final CinemaSeatsDAO p_cinemaSeatsDAO) {
		this.cinemaSeatsDAO = p_cinemaSeatsDAO;
	}

}

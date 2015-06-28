package hr.primefaces.service.impl;

import hr.primefaces.dao.ICinemaSeatsDAO;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.service.ICinemaSeatsService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "CinemaSeatsService")
@ApplicationScoped
@Transactional(readOnly = true)
public class CinemaSeatsService implements ICinemaSeatsService, Serializable {

	private static final long serialVersionUID = 1L;

	ICinemaSeatsDAO cinemaSeatsDAO;
	List<CinemaSeats> cinemaSeatsList;

	public CinemaSeatsService() {
	}

	public List<CinemaSeats> getCinemaSeatsList() {
		return cinemaSeatsList;
	}

	public void setCinemaSeatsList(List<CinemaSeats> cinemaSeatsList) {
		this.cinemaSeatsList = cinemaSeatsList;
	}

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

	public ICinemaSeatsDAO getCinemaSeatsDAO() {
		return cinemaSeatsDAO;
	}

	public void setCinemaSeatsDAO(ICinemaSeatsDAO cinemaSeatsDAO) {
		this.cinemaSeatsDAO = cinemaSeatsDAO;
	}

	@Override
	public List<CinemaSeats> getCinemaSeatsByName(String name) {
		return getCinemaSeatsDAO().getCinemaSeatsByName(name);
	}
	
	@Override
	public List<CinemaSeats> getCinemaSeatsByCinemaId(int cinemaId) {
		return getCinemaSeatsDAO().getCinemaSeatsByCinemaId(cinemaId);
	}

}

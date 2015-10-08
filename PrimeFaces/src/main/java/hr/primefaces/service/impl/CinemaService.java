package hr.primefaces.service.impl;

import hr.primefaces.dao.ICinemaDAO;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ICinemaService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "CinemaService")
@ApplicationScoped
@Transactional(readOnly = true)
public class CinemaService implements ICinemaService, Serializable {

	private static final long serialVersionUID = 1L;

	ICinemaDAO cinemaDAO;
	List<Cinema> cinemaList;

	public CinemaService() {
	}

	public List<Cinema> getCinemaList() {
		return cinemaList;
	}

	public void setCinemaList(List<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}

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

	public ICinemaDAO getCinemaDAO() {
		return cinemaDAO;
	}

	public void setCinemaDAO(ICinemaDAO cinemaDAO) {
		this.cinemaDAO = cinemaDAO;
	}

	@Override
	public List<Cinema> getCinemaByName(String name) {
		return getCinemaDAO().getCinemaByName(name);
	}

}

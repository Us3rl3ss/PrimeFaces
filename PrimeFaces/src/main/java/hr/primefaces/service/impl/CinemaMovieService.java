package hr.primefaces.service.impl;

import hr.primefaces.dao.ICinemaMovieDAO;
import hr.primefaces.model.CinemaMovie;
import hr.primefaces.service.ICinemaMovieService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.transaction.annotation.Transactional;

@ManagedBean(name = "CinemaMovieService")
@ApplicationScoped
@Transactional(readOnly = true)
public class CinemaMovieService implements ICinemaMovieService, Serializable {

	private static final long serialVersionUID = 1L;

	ICinemaMovieDAO cinemaCinemaMovieDAO;
	List<CinemaMovie> cinemaCinemaMovieList;

	public CinemaMovieService() {
	}

	public List<CinemaMovie> getCinemaMovieList() {
		return cinemaCinemaMovieList;
	}

	public void setCinemaMovieList(List<CinemaMovie> cinemaCinemaMovieList) {
		this.cinemaCinemaMovieList = cinemaCinemaMovieList;
	}

	@Transactional(readOnly = false)
	@Override
	public void addCinemaMovie(CinemaMovie cinemaCinemaMovie) {
		getCinemaMovieDAO().addCinemaMovie(cinemaCinemaMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteCinemaMovie(CinemaMovie cinemaCinemaMovie) {
		getCinemaMovieDAO().deleteCinemaMovie(cinemaCinemaMovie);
	}

	@Transactional(readOnly = false)
	@Override
	public void updateCinemaMovie(CinemaMovie cinemaCinemaMovie) {
		getCinemaMovieDAO().updateCinemaMovie(cinemaCinemaMovie);
	}

	@Override
	public CinemaMovie getCinemaMovieById(int id) {
		return getCinemaMovieDAO().getCinemaMovieById(id);
	}

	@Override
	public List<CinemaMovie> getCinemaMovies() {
		return getCinemaMovieDAO().getCinemaMovies();
	}

	public ICinemaMovieDAO getCinemaMovieDAO() {
		return cinemaCinemaMovieDAO;
	}

	public void setCinemaMovieDAO(ICinemaMovieDAO cinemaCinemaMovieDAO) {
		this.cinemaCinemaMovieDAO = cinemaCinemaMovieDAO;
	}

	@Override
	public List<CinemaMovie> getCinemaMovieByName(String name) {
		return getCinemaMovieDAO().getCinemaMovieByName(name);
	}

}

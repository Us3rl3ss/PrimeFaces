package hr.primefaces.service;

import hr.primefaces.model.Cinema;

import java.util.List;

public interface ICinemaService {

	public void addCinema(Cinema cinema);

	public void updateCinema(Cinema cinema);

	public void deleteCinema(Cinema cinema);

	public Cinema getCinemaById(int id);
	
	public List<Cinema> getCinemaByName(String name);

	public List<Cinema> getCinemas();
	
}

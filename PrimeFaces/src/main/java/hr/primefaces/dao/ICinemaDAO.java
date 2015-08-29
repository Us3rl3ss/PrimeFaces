package hr.primefaces.dao;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Theater;

import java.util.List;

public interface ICinemaDAO {

	public void addCinema(Cinema cinema);

	public void updateCinema(Cinema cinema);

	public void deleteCinema(Cinema cinema);

	public Cinema getCinemaById(int id);

	public List<Cinema> getCinemaByName(String name);
	
	public List<Cinema> getCinemas();
	
	public List<Cinema> getCinemaByTheater(Theater theater);
}

package hr.primefaces.dao;


/*
 * Imports
 */

import hr.primefaces.model.Theater;

import java.util.List;

public interface ITheaterDAO {

	public void addTheater(Theater theater);

	public void updateTheater(Theater theater);

	public void deleteTheater(Theater theater);

	public Theater getTheaterById(int id);
	
	public List<Theater> getTheaterByName(String username);

	public List<Theater> getTheaters();

	public Theater getTheaterByLatLng(Double lat, Double lng);
}

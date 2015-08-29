package hr.primefaces.service.impl;


/*
 * Imports
 */

import hr.primefaces.dao.ITheaterDAO;
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
	
	
	/*
	 * Private Attributes
	 */

	ITheaterDAO theaterDAO;
	List<Theater> theaterList;


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

	
	/*
	 * Getters and Setters
	 */

	public ITheaterDAO getTheaterDAO() {
		return theaterDAO;
	}

	public void setTheaterDAO(ITheaterDAO theaterDAO) {
		this.theaterDAO = theaterDAO;
	}

	public List<Theater> getTheaterList() {
		return theaterList;
	}

	public void setTheaterList(List<Theater> theaterList) {
		this.theaterList = theaterList;
	}

}

package hr.primefaces.bean;


/*
 * Imports
 */

import hr.primefaces.model.Theater;
import hr.primefaces.service.ITheaterService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "getTheaterMB")
@ViewScoped
public class GetTheaterManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	
	/*
	 * Managed Properties
	 */
	
	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;
	
	
	/*
	 * Private Attributes
	 */

	private Theater theater = new Theater();
	private List<Theater> theaterList;

	@PostConstruct
	public void init() {
		theaterList = theaterService.getTheaters();
	}
	
	
	/*
	 * Getters and Setters
	 */

	public ITheaterService getTheaterService() {
		return theaterService;
	}

	public void setTheaterService(ITheaterService theaterService) {
		this.theaterService = theaterService;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public List<Theater> getTheaterList() {
		return theaterList;
	}

	public void setTheaterList(List<Theater> theaterList) {
		this.theaterList = theaterList;
	}

}
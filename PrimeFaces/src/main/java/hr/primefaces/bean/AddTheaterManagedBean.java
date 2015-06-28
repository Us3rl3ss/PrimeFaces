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

import org.hibernate.HibernateException;

@ManagedBean(name = "addTheaterMB")
@ViewScoped
public class AddTheaterManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Managed Properties
	 */

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	@ManagedProperty("#{dropDownMB}")
	private DropdownMenuManagedBean dropDownMB;
	
	
	/*
	 * Private Attributes
	 */

	private Theater theater = new Theater();
	private List<Theater> theaterList;

	@PostConstruct
	public void init() {}

	public void spremi() {

		try {
			
			theaterService.addTheater(theater);
			dropDownMB.reloadTheater();
		} 
		catch (HibernateException hex) {
			hex.printStackTrace();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
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

	public DropdownMenuManagedBean getDropDownMB() {
		return dropDownMB;
	}

	public void setDropDownMB(DropdownMenuManagedBean dropDownMB) {
		this.dropDownMB = dropDownMB;
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
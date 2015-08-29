package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ICinemaService;
import hr.primefaces.service.ITheaterService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "getCinemaMB")
@ViewScoped
public class GetCinemaView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{CinemaService}")
	ICinemaService cinemaService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	private Cinema cinema = new Cinema();

	private List<Cinema> cinemaList;

	@PostConstruct
	public void init() {
		cinemaList = cinemaService.getCinemas();
	}

	public List<Theater> completeTheater(String input) {
		List<Theater> list = (List<Theater>) theaterService
				.getTheaterByName(input);
		return list;
	}

	public void handleSelect() {
		System.out.print("test");
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage("Item Selected", event.getObject()
								.toString()));
	}

	public ICinemaService getCinemaService() {
		return cinemaService;
	}

	public void setCinemaService(ICinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}

	public ITheaterService getTheaterService() {
		return theaterService;
	}

	public void setTheaterService(ITheaterService theaterService) {
		this.theaterService = theaterService;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Cinema> getCinemaList() {
		return cinemaList;
	}

	public void setCinemaList(List<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}

}
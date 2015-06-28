package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ICinemaService;
import hr.primefaces.service.ITheaterService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "addCinemaMB")
@ViewScoped
public class AddCinemaManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{CinemaService}")
	ICinemaService cinemaService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	@ManagedProperty("#{dropDownMB}")
	private DropdownMenuManagedBean dropDownMB;

	private Cinema cinema = new Cinema();

	private List<Cinema> cinemaList;

	@PostConstruct
	public void init() {
	}

	public void spremi() {

		try {
			int numberOfSeats = cinema.getNumber_of_rows() * cinema.getNumber_of_seats_in_row();
			List<CinemaSeats> cinemaSeatsList = setCinemaSeatsList(cinema.getNumber_of_rows(), cinema.getNumber_of_seats_in_row());
			
			cinema.setNumber_of_seats(numberOfSeats);
//			cinema.setCinemaSeatsList(cinemaSeatsList);
			
			Iterator<CinemaSeats> iter = cinemaSeatsList.iterator();
			while (iter.hasNext()) {
				
				CinemaSeats cs = iter.next();
				cinema.addToCinemaSeatsList(cs);
			}
			
			cinemaService.addCinema(cinema);
			dropDownMB.reloadCinema();
		} catch (Exception ex) { // TODO ERROR HANDLING
			ex.printStackTrace();
		}
	}

	private List<CinemaSeats> setCinemaSeatsList(int numberOfRows, int numberOfSeatsInRow) {

		List<CinemaSeats> result = new ArrayList<CinemaSeats>();
		
		for (int row=1; row<=numberOfRows; row++) {
			
			for (int seat=1; seat<=numberOfSeatsInRow; seat++) {
				
				CinemaSeats cs = new CinemaSeats();
				cs.setSeats_row(remapRows(row));
				cs.setSeats_number(seat);
				
				result.add(cs);
			}
		}
		
		return result;
	}
	
	// row = 1-26
	private String remapRows(int row) {
		
		String result = "";
		
		if (row > 26) {
			
			int timesLarger = (int)row/26;
			row = row - (timesLarger * 26);
			
			
			String tempChar;
			
			if (row == 0) {
				
				row = 90;
				
				tempChar = new Character((char) row).toString();
				for(int i=0; i<timesLarger; i++) {
					
					result += tempChar;
				}
			} else {
				
				row += 64;
				
				tempChar = new Character((char) row).toString();
				for(int i=0; i<=timesLarger; i++) {
					
					result += tempChar;
				}
			}
			
		} else {
			
			// A - 65
			// Z - 90
			row += 64;
			result = new Character((char) row).toString();
		}
			
		return result;
	}

	public List<Theater> completeTheater(String input) {
		// List<Theater> list = (List<Theater>) theaterService
		// .getTheaterByName(input);
		// return list;

		List<Theater> result = new ArrayList<Theater>();

		Iterator<Theater> iter = dropDownMB.getTheaterList().iterator();

		while (iter.hasNext()) {

			Theater t = iter.next();

			if (t.getName().toLowerCase().contains(input.toLowerCase()))
				result.add(t);
		}

		return result;
	}

	public void handleSelect() {
		System.out.print("test");
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
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

	public DropdownMenuManagedBean getDropDownMB() {
		return dropDownMB;
	}

	public void setDropDownMB(DropdownMenuManagedBean dropDownMB) {
		this.dropDownMB = dropDownMB;
	}

}
package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ICinemaService;
import hr.primefaces.service.ITheaterService;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "cinemaMB")
@ViewScoped
public class AddCinemaView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{CinemaService}")
	ICinemaService cinemaService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	private Cinema cinema = new Cinema();
	private Theater theater;
	private List<Theater> theaterList;
	private List<Cinema> cinemaList;
	
	public boolean deleteDisabled = true;
	public boolean saveDisabled = false;
	
	@PostConstruct
	public void init() {

		theaterList = theaterService.getTheaters();
		setTheaterList(theaterService.getTheaters());
	}
	
	/**
	 * onRowSelect
	 */
	public void onRowSelect(SelectEvent event) {
        
		cinemaList = cinemaService.getCinemaByTheater(theater);
		
		if (cinemaList.size() > 0) {
			
		}
		else {
			
			MessageUtil.info("Za odabrano kino nema unesenih kino dvorana!");
		}
    }

	/**
	 * addCinema
	 */
	public void addCinema() {
        
		cinema = new Cinema();
		cinema.setTheater(theater);
		saveDisabled = false;
		deleteDisabled = true;
		RequestContext.getCurrentInstance().execute("PF('cinemaDialog').show();");
		RequestContext.getCurrentInstance().update("cinemaDialog");
    }
	
	/**
	 * updateCinema
	 */
	public void updateCinema() {
        
		saveDisabled = true;
		deleteDisabled = false;
		RequestContext.getCurrentInstance().execute("PF('cinemaDialog').show();");
		RequestContext.getCurrentInstance().update("cinemaDialog");
    }
	
	/**
	 * save
	 */
	public void save() {

		try {
			
			if (theater != null) {
				
				if(cinemaService.getCinemaByTheaterAndName(theater, cinema.getName()) == null) {
					
					cinema.setTheater(theater);
					int numberOfSeats = cinema.getNumber_of_rows() * cinema.getNumber_of_seats_in_row();
					List<CinemaSeats> cinemaSeatsList = setCinemaSeatsList(cinema.getNumber_of_rows(), cinema.getNumber_of_seats_in_row());

					cinema.setNumber_of_seats(numberOfSeats);

					Iterator<CinemaSeats> iter = cinemaSeatsList.iterator();
					while (iter.hasNext()) {

						CinemaSeats cs = iter.next();
						cinema.addToCinemaSeatsList(cs);
					}

					cinemaService.addCinema(cinema);

					cinema = new Cinema();
					cinemaList = cinemaService.getCinemaByTheater(theater);
					MessageUtil.info("Podaci uspješno spremljeni!");
				}
				else {

					MessageUtil.info("U kinu već postoji kino dvorana sa unesenim nazivom!");
				}
			}
			else {
				
				MessageUtil.info("Niste odabrali kino!");
			}
			
		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}
	
	/**
	 * delete
	 */
	public void delete() {

		try {
			
			cinemaService.deleteCinema(cinema);
			cinemaList = cinemaService.getCinemaByTheater(theater);
			
			MessageUtil.info("Kino dvorana je uspješno obrisana!");

		} catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		} catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}

	/**
	 * setCinemaSeatsList
	 */
	private List<CinemaSeats> setCinemaSeatsList(int numberOfRows, int numberOfSeatsInRow) {

		List<CinemaSeats> result = new ArrayList<CinemaSeats>();

		for (int row = 1; row <= numberOfRows; row++) {

			for (int seat = 1; seat <= numberOfSeatsInRow; seat++) {

				CinemaSeats cs = new CinemaSeats();
				cs.setSeats_row(remapRows(row));
				cs.setSeats_number(seat);

				result.add(cs);
			}
		}

		return result;
	}

	/**
	 * remapRows row = 1-26
	 */
	private String remapRows(int row) {

		String result = "";

		if (row > 26) {

			int timesLarger = (int) row / 26;
			row = row - (timesLarger * 26);

			String tempChar;

			if (row == 0) {

				row = 90;

				tempChar = new Character((char) row).toString();
				for (int i = 0; i < timesLarger; i++) {

					result += tempChar;
				}
			} else {

				row += 64;

				tempChar = new Character((char) row).toString();
				for (int i = 0; i <= timesLarger; i++) {

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

	public List<Theater> getTheaterList() {
		return theaterList;
	}

	public void setTheaterList(List<Theater> theaterList) {
		this.theaterList = theaterList;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public List<Cinema> getCinemaList() {
		return cinemaList;
	}

	public void setCinemaList(List<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}

	public boolean isDeleteDisabled() {
		return deleteDisabled;
	}

	public void setDeleteDisabled(boolean deleteDisabled) {
		this.deleteDisabled = deleteDisabled;
	}

	public boolean isSaveDisabled() {
		return saveDisabled;
	}

	public void setSaveDisabled(boolean saveDisabled) {
		this.saveDisabled = saveDisabled;
	}

}
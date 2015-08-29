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

@ManagedBean(name = "addCinemaMB")
@ViewScoped
public class AddCinemaView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{CinemaService}")
	ICinemaService cinemaService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	private Cinema cinema = new Cinema();
	private List<Theater> theaterList;

	@PostConstruct
	public void init() {

		setTheaterList(theaterService.getTheaters());
	}

	/**
	 * spremi
	 */
	public void spremi() {

		try {
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
			MessageUtil.info("Podaci uspješno spremljeni!");
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

}
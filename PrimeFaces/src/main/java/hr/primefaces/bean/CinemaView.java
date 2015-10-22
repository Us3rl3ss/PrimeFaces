package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Theater;
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
public class CinemaView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CINEMA_DIALOG_JS = "PF('cinemaDialog').show();";
	private static final String CINEMA_DIALOG_COMPONENT = "cinemaDialog";

	@ManagedProperty(value = "#{TheaterService}")
	private ITheaterService theaterService;

	private Cinema cinema;
	private Theater theater;
	private List<Theater> theaterList;
	private List<Cinema> cinemaList;
	private boolean deleteDisabled;
	private boolean saveDisabled;

	@PostConstruct
	public void init() {

		setCinema(new Cinema());
		setDeleteDisabled(true);
		setSaveDisabled(true);

		setTheaterList(getTheaterService().getTheaters());
	}

	/**
	 * save
	 */
	public void save() {

		try {

			if (getTheater() != null) {

				if(getTheaterService().getCinemaByTheaterAndName(getTheater(), getCinema().getName()) == null) {

					getCinema().setTheater(getTheater());
					final int numberOfSeats = getCinema().getNumberOfRows() * getCinema().getNumberOfSeatsInRow();
					final List<CinemaSeats> cinemaSeatsList = setCinemaSeatsList(getCinema().getNumberOfRows(),
							getCinema().getNumberOfSeatsInRow());
					getCinema().setNumberOfSeats(numberOfSeats);

					final Iterator<CinemaSeats> iter = cinemaSeatsList.iterator();
					while (iter.hasNext()) {

						final CinemaSeats cs = iter.next();
						getCinema().addToCinemaSeatsList(cs);
					}

					getTheaterService().addCinema(getCinema());
					setCinema(new Cinema());
					setCinemaList(getTheaterService().getCinemaByTheater(getTheater()));
					MessageUtil.info("Podaci uspješno spremljeni!");
				}
				else {

					MessageUtil.info("U kinu već postoji kino dvorana sa unesenim nazivom!");
				}
			}
			else {

				MessageUtil.info("Niste odabrali kino!");
			}
		}
		catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}

	/**
	 * delete
	 */
	public void delete() {

		try {

			getTheaterService().deleteCinema(getCinema());
			setCinemaList(getTheaterService().getCinemaByTheater(getTheater()));

			MessageUtil.info("Kino dvorana je uspješno obrisana!");
		}
		catch (HibernateException hex) {
			hex.printStackTrace();
			MessageUtil.error("Došlo je do hibernate greške!");
		}
		catch (Exception ex) {
			ex.printStackTrace();
			MessageUtil.error("Došlo je do greške!");
		}
	}

	/**
	 * addCinema
	 */
	public void addCinema() {

		setCinema(new Cinema());
		getCinema().setTheater(getTheater());
		setSaveDisabled(false);
		setDeleteDisabled(true);

		RequestContext.getCurrentInstance().execute(CINEMA_DIALOG_JS);
		RequestContext.getCurrentInstance().update(CINEMA_DIALOG_COMPONENT);
    }

	/**
	 * updateCinema
	 */
	public void updateCinema() {

		setSaveDisabled(true);
		setDeleteDisabled(false);

		RequestContext.getCurrentInstance().execute(CINEMA_DIALOG_JS);
		RequestContext.getCurrentInstance().update(CINEMA_DIALOG_COMPONENT);
    }

	/**
	 * setCinemaSeatsList
	 */
	private List<CinemaSeats> setCinemaSeatsList(final int p_numberOfRows, final int p_numberOfSeatsInRow) {

		final List<CinemaSeats> result = new ArrayList<CinemaSeats>();

		for (int row = 1; row <= p_numberOfRows; row++) {

			for (int seat = 1; seat <= p_numberOfSeatsInRow; seat++) {

				final CinemaSeats cs = new CinemaSeats();
				cs.setSeatsRow(remapRows(row));
				cs.setSeatsNumber(seat);

				result.add(cs);
			}
		}

		return result;
	}

	/**
	 * remapRows row = 1-26
	 */
	private String remapRows(final int p_row) {

		String result = "";

		int outputRow = p_row;

		if (outputRow > 26) {

			final int timesLarger = (int) outputRow / 26;
			outputRow = outputRow - (timesLarger * 26);

			String tempChar;

			if (outputRow == 0) {

				outputRow = 90;

				tempChar = new Character((char) outputRow).toString();
				for (int i = 0; i < timesLarger; i++) {

					result += tempChar;
				}
			}
			else {

				outputRow += 64;

				tempChar = new Character((char) outputRow).toString();
				for (int i = 0; i <= timesLarger; i++) {

					result += tempChar;
				}
			}

		}
		else {

			// A - 65
			// Z - 90
			outputRow += 64;
			result = new Character((char) outputRow).toString();
		}

		return result;
	}

	/**
	 * onRowSelect
	 */
	public void onRowSelect(final SelectEvent p_event) {

		setCinemaList(getTheaterService().getCinemaByTheater(getTheater()));

		if (!(getCinemaList().size() > 0)) {

			MessageUtil.info("Za odabrano kino nema unesenih kino dvorana!");
		}
    }

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the theaterService
	 */
	public ITheaterService getTheaterService() {
		return theaterService;
	}

	/**
	 * @return the cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * @return the theater
	 */
	public Theater getTheater() {
		return theater;
	}

	/**
	 * @return the theaterList
	 */
	public List<Theater> getTheaterList() {
		return theaterList;
	}

	/**
	 * @return the cinemaList
	 */
	public List<Cinema> getCinemaList() {
		return cinemaList;
	}

	/**
	 * @return the deleteDisabled
	 */
	public boolean isDeleteDisabled() {
		return deleteDisabled;
	}

	/**
	 * @return the saveDisabled
	 */
	public boolean isSaveDisabled() {
		return saveDisabled;
	}

	/**
	 * @param p_theaterService the theaterService to set
	 */
	public void setTheaterService(final ITheaterService p_theaterService) {
		this.theaterService = p_theaterService;
	}

	/**
	 * @param p_cinema the cinema to set
	 */
	public void setCinema(final Cinema p_cinema) {
		this.cinema = p_cinema;
	}

	/**
	 * @param p_theater the theater to set
	 */
	public void setTheater(final Theater p_theater) {
		this.theater = p_theater;
	}

	/**
	 * @param p_theaterList the theaterList to set
	 */
	public void setTheaterList(final List<Theater> p_theaterList) {
		this.theaterList = p_theaterList;
	}

	/**
	 * @param p_cinemaList the cinemaList to set
	 */
	public void setCinemaList(final List<Cinema> p_cinemaList) {
		this.cinemaList = p_cinemaList;
	}

	/**
	 * @param p_deleteDisabled the deleteDisabled to set
	 */
	public void setDeleteDisabled(final boolean p_deleteDisabled) {
		this.deleteDisabled = p_deleteDisabled;
	}

	/**
	 * @param p_saveDisabled the saveDisabled to set
	 */
	public void setSaveDisabled(final boolean p_saveDisabled) {
		this.saveDisabled = p_saveDisabled;
	}

}
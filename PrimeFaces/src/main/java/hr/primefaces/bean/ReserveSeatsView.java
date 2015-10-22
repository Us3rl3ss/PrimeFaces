package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.ProjectionReservedSeats;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IProjectionService;
import hr.primefaces.service.ITheaterService;
import hr.primefaces.util.DateConverter;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "reserveSeatsMB")
@ViewScoped
public class ReserveSeatsView implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SET_SEATS_JS = "ReserveSeats.setSeats();";

	@ManagedProperty(value = "#{userSession}")
	private UserSession userSession;

	@ManagedProperty(value = "#{TheaterService}")
	private ITheaterService theaterService;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	@ManagedProperty(value = "#{ProjectionService}")
	private IProjectionService projectionService;

	@ManagedProperty(value = "#{navigationController}")
	private NavigationControllerBean navigationController;

	private Projection projection;
	private List<CinemaSeats> cinemaSeatsList;
	private List<String> selectedCinemaSeatsList;
	private List<String> savedCinemaSeatsList;
	private List<String> savedCinemaSeatsByCurrUserList;
	private String projectionStart;
	private String projectionEnd;

	@PostConstruct
	public void init() {

		if (getProjection() != null) {

			final Movie tempMovie = getProjection().getMovie();

			final List<Actor> actorList = getMovieService().getAllMovieActors(tempMovie);
			final List<Genre> genreList = getMovieService().getAllMovieGenres(tempMovie);

			getProjection().getMovie().setActorList(actorList);
			getProjection().getMovie().setListOfActorsText(getListOfActorsText(actorList));

			getProjection().getMovie().setGenreList(genreList);
			getProjection().getMovie().setListOfGenresText(getListOfGenresText(genreList));

			final int numberOfSeats = getProjection().getCinema().getNumberOfSeats();
			final int numberOfFreeSeats = getProjectionService().getProjectionReservedSeatsByProjection(getProjection()).size();

			final String numberOfFreeSeatsText = numberOfFreeSeats + "/" + numberOfSeats;

			getProjection().setNumberOfFreeSeatsText(numberOfFreeSeatsText);

			setProjectionStart(DateConverter.covertDateToString(getProjection().getStartTime(), DateConverter.HH_mm));
			setProjectionEnd(DateConverter.covertDateToString(getProjection().getEndTime(), DateConverter.HH_mm));

			setSeats();
			setSelectedSeats();

			RequestContext.getCurrentInstance().execute(SET_SEATS_JS);
		}
	}

	/**
	 * reserveSeats
	 * @return
	 */
	public String reserveSeats() {

		if (getUserSession().getUser() != null) {

			final List<ProjectionReservedSeats> seatsToSaveList = new ArrayList<ProjectionReservedSeats>();

			final List<String> cinemaSeatIdToSaveList = getNewReservations(getSelectedCinemaSeatsList(), getSavedCinemaSeatsList());

			final Iterator<String> iter = cinemaSeatIdToSaveList.iterator();

			while (iter.hasNext()) {

				final CinemaSeats cinemaSeats = new CinemaSeats();
				cinemaSeats.setId(Integer.parseInt(iter.next()));

				final ProjectionReservedSeats prs = new ProjectionReservedSeats();
				prs.setProjection(getProjection());
				prs.setCinemaSeats(cinemaSeats);
				prs.setUser(getUserSession().getUser());

				seatsToSaveList.add(prs);
			}

			if (seatsToSaveList.size() > 0) {

				final Iterator<ProjectionReservedSeats> saveIter = seatsToSaveList.iterator();

				while (saveIter.hasNext()) {

					final ProjectionReservedSeats prs = saveIter.next();
					getProjectionService().addProjectionReservedSeats(prs);
				}
			}

			MessageUtil.info("Rezervacija je uspješna!");
			return getNavigationController().doViewProjection();
		}
		else {
			MessageUtil.info("Da biste rezervirali mjesto morate imati korisnički račun i biti prijavljeni u sustav!");
			return "";
		}
	}

	/**
	 * getNewReservations
	 * @param p_selectedCinemaSeatsList
	 * @param p_savedCinemaSeatsList
	 * @return
	 */
	private List<String> getNewReservations(final List<String> p_selectedCinemaSeatsList,
			final List<String> p_savedCinemaSeatsList) {

		final List<String> result = new ArrayList<String>();

		final Iterator<String> iterSelected = p_selectedCinemaSeatsList.iterator();

		while (iterSelected.hasNext()) {

			final String tempSelected = iterSelected.next();

			boolean inList = false;
			for (int i = 0; i < p_savedCinemaSeatsList.size(); i++) {

				final String tempSaved = p_savedCinemaSeatsList.get(i);

				if (tempSelected.equals(tempSaved)) {
					inList = true;
				}
			}

			if (!inList) {
				result.add(tempSelected);
			}
		}

		return result;
	}

	/**
	 * pullValuesFromFlash
	 * @param p_e
	 */
	public void pullValuesFromFlash(final ComponentSystemEvent p_e) {

		final Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		final Projection flashProjection = (Projection) flash.get("projection");
		setProjection(getProjectionService().getProjectionById(flashProjection.getId()));

		init();
	}

	/**
	 * setSeats
	 */
	public void setSeats() {

		this.setCinemaSeatsList(getTheaterService().getCinemaSeatsByCinemaId(getProjection().getCinema().getId()));
	}

	/**
	 * setSelectedSeats
	 */
	public void setSelectedSeats() {

		final List<ProjectionReservedSeats> prsList = getProjectionService().getProjectionReservedSeatsByProjection(getProjection());
		final Iterator<ProjectionReservedSeats> iter = prsList.iterator();

		setSelectedCinemaSeatsList(new ArrayList<String>());
		setSavedCinemaSeatsList(new ArrayList<String>());

		while (iter.hasNext()) {

			final ProjectionReservedSeats prs = iter.next();
			final String cinemaSeatId = prs.getCinemaSeats().getId() + "";

			getSelectedCinemaSeatsList().add(cinemaSeatId);
			getSavedCinemaSeatsList().add(cinemaSeatId);
		}
	}

	/**
	 * setSavedCinemaSeatsByCurrUserList
	 */
	public void setSavedCinemaSeatsByCurrUserList() {

		final List<ProjectionReservedSeats> prsList = getProjectionService()
				.getProjectionReservedSeatsByProjectionAndUser(getProjection(), getUserSession().getUser());
		final Iterator<ProjectionReservedSeats> iter = prsList.iterator();

		while (iter.hasNext()) {

			final ProjectionReservedSeats prs = iter.next();
			final String cinemaSeatId = prs.getCinemaSeats().getId() + "";

			getSavedCinemaSeatsByCurrUserList().add(cinemaSeatId);
		}
	}

	/**
	 * getListOfActorsText
	 * @param p_actorList
	 * @return
	 */
	public String getListOfActorsText(final List<Actor> p_actorList) {

		String result = "";

		if (p_actorList.size() > 0) {

			final Iterator<Actor> iter = p_actorList.iterator();

			while (iter.hasNext()) {

				final Actor a = iter.next();

				result += a.getFirstname() + " " + a.getLastname();
				result += ", ";
			}

			result = result.substring(0, result.length() - 2);
		}

		if (result.length() == 0) {
			result = "-";
		}

		return result;
	}

	/**
	 * getListOfGenresText
	 * @param p_genreList
	 * @return
	 */
	public String getListOfGenresText(final List<Genre> p_genreList) {

		String result = "";

		if (p_genreList.size() > 0) {

			final Iterator<Genre> iter = p_genreList.iterator();

			while (iter.hasNext()) {

				final Genre g = iter.next();

				result += g.getName();
				result += ", ";
			}

			result = result.substring(0, result.length() - 2);
		}

		if (result.length() == 0) {
			result = "-";
		}

		return result;
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the userSession
	 */
	public UserSession getUserSession() {
		return userSession;
	}

	/**
	 * @return the theaterService
	 */
	public ITheaterService getTheaterService() {
		return theaterService;
	}

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @return the projectionService
	 */
	public IProjectionService getProjectionService() {
		return projectionService;
	}

	/**
	 * @return the navigationController
	 */
	public NavigationControllerBean getNavigationController() {
		return navigationController;
	}

	/**
	 * @return the projection
	 */
	public Projection getProjection() {
		return projection;
	}

	/**
	 * @return the cinemaSeatsList
	 */
	public List<CinemaSeats> getCinemaSeatsList() {
		return cinemaSeatsList;
	}

	/**
	 * @return the selectedCinemaSeatsList
	 */
	public List<String> getSelectedCinemaSeatsList() {
		return selectedCinemaSeatsList;
	}

	/**
	 * @return the savedCinemaSeatsList
	 */
	public List<String> getSavedCinemaSeatsList() {
		return savedCinemaSeatsList;
	}

	/**
	 * @return the savedCinemaSeatsByCurrUserList
	 */
	public List<String> getSavedCinemaSeatsByCurrUserList() {
		return savedCinemaSeatsByCurrUserList;
	}

	/**
	 * @return the projectionStart
	 */
	public String getProjectionStart() {
		return projectionStart;
	}

	/**
	 * @return the projectionEnd
	 */
	public String getProjectionEnd() {
		return projectionEnd;
	}

	/**
	 * @param p_userSession the userSession to set
	 */
	public void setUserSession(final UserSession p_userSession) {
		this.userSession = p_userSession;
	}

	/**
	 * @param p_theaterService the theaterService to set
	 */
	public void setTheaterService(final ITheaterService p_theaterService) {
		this.theaterService = p_theaterService;
	}

	/**
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

	/**
	 * @param p_projectionService the projectionService to set
	 */
	public void setProjectionService(final IProjectionService p_projectionService) {
		this.projectionService = p_projectionService;
	}

	/**
	 * @param p_navigationController the navigationController to set
	 */
	public void setNavigationController(final NavigationControllerBean p_navigationController) {
		this.navigationController = p_navigationController;
	}

	/**
	 * @param p_projection the projection to set
	 */
	public void setProjection(final Projection p_projection) {
		this.projection = p_projection;
	}

	/**
	 * @param p_cinemaSeatsList the cinemaSeatsList to set
	 */
	public void setCinemaSeatsList(final List<CinemaSeats> p_cinemaSeatsList) {
		this.cinemaSeatsList = p_cinemaSeatsList;
	}

	/**
	 * @param p_selectedCinemaSeatsList the selectedCinemaSeatsList to set
	 */
	public void setSelectedCinemaSeatsList(final List<String> p_selectedCinemaSeatsList) {
		this.selectedCinemaSeatsList = p_selectedCinemaSeatsList;
	}

	/**
	 * @param p_savedCinemaSeatsList the savedCinemaSeatsList to set
	 */
	public void setSavedCinemaSeatsList(final List<String> p_savedCinemaSeatsList) {
		this.savedCinemaSeatsList = p_savedCinemaSeatsList;
	}

	/**
	 * @param p_savedCinemaSeatsByCurrUserList the savedCinemaSeatsByCurrUserList to set
	 */
	public void setSavedCinemaSeatsByCurrUserList(final List<String> p_savedCinemaSeatsByCurrUserList) {
		this.savedCinemaSeatsByCurrUserList = p_savedCinemaSeatsByCurrUserList;
	}

	/**
	 * @param p_projectionStart the projectionStart to set
	 */
	public void setProjectionStart(final String p_projectionStart) {
		this.projectionStart = p_projectionStart;
	}

	/**
	 * @param p_projectionEnd the projectionEnd to set
	 */
	public void setProjectionEnd(final String p_projectionEnd) {
		this.projectionEnd = p_projectionEnd;
	}

}
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

import com.google.gson.Gson;

@ManagedBean(name = "reserveSeatsMB")
@ViewScoped
public class ReserveSeatsView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userSession}")
	UserSession userSession;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;
	
	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	@ManagedProperty(value = "#{ProjectionService}")
	IProjectionService projectionService;

	@ManagedProperty(value = "#{navigationController}")
	NavigationControllerBean navigationController;

	private Projection projection;
	private List<CinemaSeats> cinemaSeatsList;
	private List<String> selectedCinemaSeatsList;
	private List<String> savedCinemaSeatsList;
	
	private List<String> savedCinemaSeatsByCurrUserList;
	
	private String projectionStart;
	private String projectionEnd;

	@PostConstruct
	public void init() {

		if (projection != null) {
			
			Movie tempMovie = projection.getMovie();
			
			List<Actor> actorList = movieService.getAllMovieActors(tempMovie);
			List<Genre> genreList = movieService.getAllMovieGenres(tempMovie);
			
			projection.getMovie().setActorList(actorList);
			projection.getMovie().setListOfActorsText(getListOfActorsText(actorList));
			
			projection.getMovie().setGenreList(genreList);
			projection.getMovie().setListOfGenresText(getListOfGenresText(genreList));
			
			int numberOfSeats = projection.getCinema().getNumber_of_seats();
			int numberOfFreeSeats = projectionService.getProjectionReservedSeatsByProjection(projection).size();

			String numberOfFreeSeatsText = numberOfFreeSeats + "/" + numberOfSeats;

			projection.setNumberOfFreeSeatsText(numberOfFreeSeatsText);
			
			projectionStart = DateConverter.covertDateToString(projection.getStart_time(), DateConverter.HH_mm);
			projectionEnd = DateConverter.covertDateToString(projection.getEnd_time(), DateConverter.HH_mm);
			
			setSeats();
			setSelectedSeats();
			
			RequestContext.getCurrentInstance().execute("ReserveSeats.setSeats()");
		}
	}
	
	public void sendDataToJS() {
		
		Gson gson = new Gson();
		Projection p = new Projection();
		p.setId(5);
		String res = gson.toJson(p);
		
		String call = "ReserveSeats.setSeats(" + res + ")";
		RequestContext.getCurrentInstance().execute(call);
	}

	public String reserveSeats() {
		
		if (userSession.getUser() != null) {
			
			List<ProjectionReservedSeats> seatsToSaveList = new ArrayList<ProjectionReservedSeats>();
			
			List<String> cinemaSeatIdToSaveList = getNewReservations(
					this.selectedCinemaSeatsList, this.savedCinemaSeatsList);
			
			Iterator<String> iter = cinemaSeatIdToSaveList.iterator();
			
			while (iter.hasNext()) {
				
				CinemaSeats cinemaSeats = new CinemaSeats();
				cinemaSeats.setId(Integer.parseInt(iter.next()));
				
				ProjectionReservedSeats prs = new ProjectionReservedSeats();
				prs.setProjection(this.projection);
				prs.setCinema_seats(cinemaSeats);
				prs.setUser(userSession.getUser());
				
				seatsToSaveList.add(prs);
			}
			
			if (seatsToSaveList.size() > 0) {
				
				Iterator<ProjectionReservedSeats> saveIter = seatsToSaveList
						.iterator();
				
				while (saveIter.hasNext()) {
					
					ProjectionReservedSeats prs = saveIter.next();
					projectionService.addProjectionReservedSeats(prs);
				}
			}
			
			MessageUtil.info("Rezervacija je uspješna!");
			return navigationController.doViewProjection();
		}
		else {
			MessageUtil.info("Da biste rezervirali mjesto morate imati korisnički račun i biti prijavljeni u sustav!");
			return "";
		}
	}

	private List<String> getNewReservations(
			List<String> selectedCinemaSeatsList,
			List<String> savedCinemaSeatsList) {

		List<String> result = new ArrayList<String>();

		Iterator<String> iterSelected = selectedCinemaSeatsList.iterator();

		while (iterSelected.hasNext()) {

			String tempSelected = iterSelected.next();

			boolean inList = false;
			for (int i = 0; i < savedCinemaSeatsList.size(); i++) {

				String tempSaved = savedCinemaSeatsList.get(i);

				if (tempSelected.equals(tempSaved))
					inList = true;
			}

			if (!inList)
				result.add(tempSelected);
		}

		return result;
	}

	public void pullValuesFromFlash(ComponentSystemEvent e) {
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();

		Projection flashProjection = (Projection) flash.get("projection");

		projection = projectionService.getProjectionById(flashProjection.getId());
		
		init();
	}

	public void setSeats() {

		this.setCinemaSeatsList(theaterService
				.getCinemaSeatsByCinemaId(projection.getCinema().getId()));
	}

	public void setSelectedSeats() {

		List<ProjectionReservedSeats> prsList = projectionService
				.getProjectionReservedSeatsByProjection(this.projection);

		Iterator<ProjectionReservedSeats> iter = prsList.iterator();

		selectedCinemaSeatsList = new ArrayList<String>();
		savedCinemaSeatsList = new ArrayList<String>();

		while (iter.hasNext()) {

			ProjectionReservedSeats prs = iter.next();

			String cinemaSeatId = prs.getCinema_seats().getId() + "";

			selectedCinemaSeatsList.add(cinemaSeatId);
			savedCinemaSeatsList.add(cinemaSeatId);
		}
	}
	
	public void setSavedCinemaSeatsByCurrUserList() {

		List<ProjectionReservedSeats> prsList = projectionService
				.getProjectionReservedSeatsByProjectionAndUser(this.projection, userSession.getUser());

		Iterator<ProjectionReservedSeats> iter = prsList.iterator();

		while (iter.hasNext()) {

			ProjectionReservedSeats prs = iter.next();

			String cinemaSeatId = prs.getCinema_seats().getId() + "";

			savedCinemaSeatsByCurrUserList.add(cinemaSeatId);
		}
	}

	/**
	 * getListOfActorsText
	 */
	public String getListOfActorsText(List<Actor> actorList) {

		String result = "";

		if (actorList.size() > 0) {

			Iterator<Actor> iter = actorList.iterator();

			while (iter.hasNext()) {

				Actor a = iter.next();

				result += a.getFirstname() + " " + a.getLastname();
				result += ", ";
			}

			result = result.substring(0, result.length() - 2);
		}

		if (result.length() == 0)
			result = "-";

		return result;
	}
	
	/**
	 * getListOfGenresText
	 */
	public String getListOfGenresText(List<Genre> genreList) {

		String result = "";

		if (genreList.size() > 0) {

			Iterator<Genre> iter = genreList.iterator();

			while (iter.hasNext()) {

				Genre g = iter.next();

				result += g.getName();
				result += ", ";
			}

			result = result.substring(0, result.length() - 2);
		}

		if (result.length() == 0)
			result = "-";

		return result;
	}

	public void spremi() {
	}

	public void pretrazi() {
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public List<CinemaSeats> getCinemaSeatsList() {
		return cinemaSeatsList;
	}

	public void setCinemaSeatsList(List<CinemaSeats> cinemaSeatsList) {
		this.cinemaSeatsList = cinemaSeatsList;
	}

	public List<String> getSelectedCinemaSeatsList() {
		return selectedCinemaSeatsList;
	}

	public void setSelectedCinemaSeatsList(List<String> selectedCinemaSeatsList) {
		this.selectedCinemaSeatsList = selectedCinemaSeatsList;
	}

	public List<String> getSavedCinemaSeatsList() {
		return savedCinemaSeatsList;
	}

	public void setSavedCinemaSeatsList(List<String> savedCinemaSeatsList) {
		this.savedCinemaSeatsList = savedCinemaSeatsList;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public List<String> getSavedCinemaSeatsByCurrUserList() {
		return savedCinemaSeatsByCurrUserList;
	}

	public void setSavedCinemaSeatsByCurrUserList(List<String> savedCinemaSeatsByCurrUserList) {
		this.savedCinemaSeatsByCurrUserList = savedCinemaSeatsByCurrUserList;
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public IProjectionService getProjectionService() {
		return projectionService;
	}

	public void setProjectionService(IProjectionService projectionService) {
		this.projectionService = projectionService;
	}

	public NavigationControllerBean getNavigationController() {
		return navigationController;
	}

	public void setNavigationController(NavigationControllerBean navigationController) {
		this.navigationController = navigationController;
	}

	public String getProjectionStart() {
		return projectionStart;
	}

	public void setProjectionStart(String projectionStart) {
		this.projectionStart = projectionStart;
	}

	public String getProjectionEnd() {
		return projectionEnd;
	}

	public void setProjectionEnd(String projectionEnd) {
		this.projectionEnd = projectionEnd;
	}

	/**
	 * @return the theaterService
	 */
	public ITheaterService getTheaterService() {
		return theaterService;
	}

	/**
	 * @param theaterService the theaterService to set
	 */
	public void setTheaterService(ITheaterService theaterService) {
		this.theaterService = theaterService;
	}

}
package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ICinemaService;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IProjectionReservedSeatsService;
import hr.primefaces.service.IProjectionService;
import hr.primefaces.service.ITheaterService;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "projectionMB")
@ViewScoped
public class ProjectionView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ProjectionService}")
	IProjectionService projectionService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	@ManagedProperty(value = "#{CinemaService}")
	ICinemaService cinemaService;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	@ManagedProperty(value = "#{ProjectionReservedSeatsService}")
	IProjectionReservedSeatsService projectionReservedSeatsService;

	private Projection projection = new Projection();
	private Theater theater = new Theater();
	private Cinema cinema = new Cinema();
	private Movie movie = new Movie();

	private List<Projection> projectionList;
	private List<Theater> theaterList;
	private List<Cinema> cinemaList;
	private List<Movie> movieList;

	private Projection selectedProjection;
	private List<Projection> distinctMovieProjectionList;

	private boolean render;

	@PostConstruct
	public void init() {
		theaterList = theaterService.getTheaters();
	}
	
	/**
	 * doViewReserveSeats
	 */
	public String doViewReserveSeats() {
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);

		flash.put("projection", projection);

		return "reserveSeats?faces-redirect=true";
	}

	/**
	 * pretrazi
	 */
	public void pretrazi() {

		List<Projection> tempProjectionList = projectionService.getProjectionsForReservation(theater);

		for (int i = 0; i < tempProjectionList.size(); i++) {

			Projection tempProjection = tempProjectionList.get(i);
			Movie tempMovie = tempProjection.getMovie();

			List<Actor> actorList = movieService.getAllMovieActors(tempMovie);
			List<Genre> genreList = movieService.getAllMovieGenres(tempMovie);

			tempProjectionList.get(i).getMovie().setActorList(actorList);
			tempProjectionList.get(i).getMovie().setListOfActorsText(getListOfActorsText(actorList));

			tempProjectionList.get(i).getMovie().setGenreList(genreList);
			tempProjectionList.get(i).getMovie().setListOfGenresText(getListOfGenresText(genreList));
		}

		projectionList = tempProjectionList;
		RequestContext.getCurrentInstance().update("projection");
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
	 * doViewDistinctMovieProjections
	 */
	public void doViewDistinctMovieProjections() {

		List<Projection> tempProjectionList = projectionService.getDistinctMovieProjections(selectedProjection);

		for (int i = 0; i < tempProjectionList.size(); i++) {

			Projection tempProjection = tempProjectionList.get(i);

			int numberOfSeats = tempProjection.getCinema().getNumber_of_seats();
			int numberOfFreeSeats = projectionReservedSeatsService.getProjectionReservedSeatsByProjection(tempProjection).size();

			String numberOfFreeSeatsText = numberOfFreeSeats + "/" + numberOfSeats;

			tempProjectionList.get(i).setNumberOfFreeSeatsText(numberOfFreeSeatsText);
		}

		distinctMovieProjectionList = tempProjectionList;
	}

	public IProjectionService getProjectionService() {
		return projectionService;
	}

	public void setProjectionService(IProjectionService projectionService) {
		this.projectionService = projectionService;
	}

	public ITheaterService getTheaterService() {
		return theaterService;
	}

	public void setTheaterService(ITheaterService theaterService) {
		this.theaterService = theaterService;
	}

	public ICinemaService getCinemaService() {
		return cinemaService;
	}

	public void setCinemaService(ICinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public List<Projection> getProjectionList() {
		return projectionList;
	}

	public void setProjectionList(List<Projection> projectionList) {
		this.projectionList = projectionList;
	}

	public List<Theater> getTheaterList() {
		return theaterList;
	}

	public void setTheaterList(List<Theater> theaterList) {
		this.theaterList = theaterList;
	}

	public List<Cinema> getCinemaList() {
		return cinemaList;
	}

	public void setCinemaList(List<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public List<Projection> getDistinctMovieProjectionList() {
		return distinctMovieProjectionList;
	}

	public void setDistinctMovieProjectionList(List<Projection> distinctMovieProjectionList) {
		this.distinctMovieProjectionList = distinctMovieProjectionList;
	}

	public Projection getSelectedProjection() {
		return selectedProjection;
	}

	public void setSelectedProjection(Projection selectedProjection) {
		this.selectedProjection = selectedProjection;
	}

	public IProjectionReservedSeatsService getProjectionReservedSeatsService() {
		return projectionReservedSeatsService;
	}

	public void setProjectionReservedSeatsService(IProjectionReservedSeatsService projectionReservedSeatsService) {
		this.projectionReservedSeatsService = projectionReservedSeatsService;
	}

}
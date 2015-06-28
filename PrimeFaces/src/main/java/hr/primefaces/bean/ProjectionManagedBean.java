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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "projectionMB")
@ViewScoped
public class ProjectionManagedBean implements Serializable {

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

	@ManagedProperty("#{dropDownMB}")
	private DropdownMenuManagedBean dropDownMB;

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

	public String doViewReserveSeats() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);

		flash.put("projection", projection);

		return "reserveSeats?faces-redirect=true";
		// return "reserveSeats";
	}

	@PostConstruct
	public void init() {
		// projectionList = projectionService.getProjectionsForReservation();
		// //getProjections();

		System.out.println();
		theaterList = dropDownMB.getTheaterList();// theaterService.getTheaters();
		// cinemaList = cinemaService.getCinemas();
		// movieList = movieService.getMovies();
	}

	public void pretrazi() {

		// System.out.println(this.theater.getName());
		//
		// if (theater.getName().equals("Novo"))
		// this.render = true;
		// else
		// this.render = false;

		// System.out.println("PRETRAŽI");
		//
		// Iterator<Projection> iter =
		// dropDownMB.getProjectionList().iterator();
		//
		// this.projectionList = new ArrayList<Projection>();
		//
		// while (iter.hasNext()) {
		//
		// Projection p = iter.next();
		//
		// if (p.getTheater().getId().equals(theater.getId()))
		// this.projectionList.add(p);
		// }

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

		// this.projectionList =
		// projectionService.getProjectionsByTheater(theater);//new
		// ArrayList<Projection>();

		// this.projectionList.add(projectionService.getProjectionById(3));

	}

	private String getListOfGenresText(List<Genre> genreList) {

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

	private String getListOfActorsText(List<Actor> actorList) {

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

	public void postaviCinemaList(ValueChangeEvent event) throws AbortProcessingException {

		Theater theater = (Theater) event.getNewValue();

		List<Object[]> list = theaterService.getTheaterJoinCinemaById(theater.getId());

		List<Cinema> cinemaList = new ArrayList<Cinema>();
		for (Object[] arr : list) {

			Theater t = (Theater) arr[0];
			Cinema c = (Cinema) arr[1];
			cinemaList.add(c);
		}

		this.cinemaList = cinemaList;
	}

	private void ispisProjection(Projection p) {

		System.out.println("PROJECTION: ");

		System.out.println("	id:" + p.getId());
		System.out.println("	start_time:" + p.getStart_time());
		System.out.println("	end_time:" + p.getEnd_time());
		System.out.println("	movie|id:" + p.getMovie().getId());
		System.out.println("	movie|name:" + p.getMovie().getName());
		System.out.println("	cinema|id:" + p.getCinema().getId());
		System.out.println("	cinema|name:" + p.getCinema().getName());

		System.out.println("END OF - PROJECTION: ");
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public DropdownMenuManagedBean getDropDownMB() {
		return dropDownMB;
	}

	public void setDropDownMB(DropdownMenuManagedBean dropDownMB) {
		this.dropDownMB = dropDownMB;
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
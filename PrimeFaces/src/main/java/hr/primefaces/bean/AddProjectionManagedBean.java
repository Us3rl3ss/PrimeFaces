package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ICinemaService;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IProjectionService;
import hr.primefaces.service.ITheaterService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "addProjectionMB")
@ViewScoped
public class AddProjectionManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ProjectionService}")
	IProjectionService projectionService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	@ManagedProperty(value = "#{CinemaService}")
	ICinemaService cinemaService;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

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

	private boolean render;

	@PostConstruct
	public void init() {
		theaterList = dropDownMB.getTheaterList(); // theaterService.getTheaters();
		cinemaList = dropDownMB.getCinemaList();
		movieList = dropDownMB.getMovieList();
	}

	public void spremi() {

		try {
			ispisProjection(projection);

			projectionService.addProjection(projection);
			dropDownMB.reloadProjection();
		} catch (Exception ex) { // TODO ERROR HANDLING
			ex.printStackTrace();
		}
	}

	public void pretrazi() {

		System.out.println(this.theater.getName());

		if (theater.getName().equals("Novo"))
			this.render = true;
		else
			this.render = false;
	}

	public void postaviCinemaList(ValueChangeEvent event)
			throws AbortProcessingException {

		Theater theater = (Theater) event.getNewValue();

		List<Object[]> list = theaterService.getTheaterJoinCinemaById(theater
				.getId());

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

}
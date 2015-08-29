package hr.primefaces.bean;

import hr.primefaces.model.Cinema;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;
import hr.primefaces.service.ICinemaService;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IProjectionService;
import hr.primefaces.service.ITheaterService;
import hr.primefaces.util.MessageUtil;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;

import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "addProjectionMB")
@ViewScoped
public class AddProjectionView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ProjectionService}")
	IProjectionService projectionService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	@ManagedProperty(value = "#{CinemaService}")
	ICinemaService cinemaService;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

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
		theaterList = theaterService.getTheaters();
		cinemaList = cinemaService.getCinemaByTheater(theaterList.get(0));
		movieList = movieService.getMovies();
	}

	/**
	 * spremi
	 */
	public void spremi() {

		try {
			projectionService.addProjection(projection);
			projection = new Projection();
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
	 * postaviCinemaList
	 * @param event
	 * @throws AbortProcessingException
	 */
	public void postaviCinemaList(){

		cinemaList = cinemaService.getCinemaByTheater(projection.getTheater());
		RequestContext.getCurrentInstance().update("addProjection");
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

}
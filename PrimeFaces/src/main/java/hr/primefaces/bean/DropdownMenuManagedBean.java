package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Cinema;
import hr.primefaces.model.CinemaSeats;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Projection;
import hr.primefaces.model.Theater;
import hr.primefaces.service.IActorService;
import hr.primefaces.service.ICinemaSeatsService;
import hr.primefaces.service.ICinemaService;
import hr.primefaces.service.IGenreService;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IProjectionService;
import hr.primefaces.service.ITheaterService;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "dropDownMB")
@ApplicationScoped
public class DropdownMenuManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{ProjectionService}")
	IProjectionService projectionService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	@ManagedProperty(value = "#{CinemaService}")
	ICinemaService cinemaService;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	@ManagedProperty(value = "#{ActorService}")
	IActorService actorService;

	@ManagedProperty(value = "#{GenreService}")
	IGenreService genreService;

	@ManagedProperty(value = "#{CinemaSeatsService}")
	ICinemaSeatsService cinemaSeatsService;

	private Projection projection = new Projection();
	private Theater theater = new Theater();
	private Cinema cinema = new Cinema();
	private Movie movie = new Movie();
	private Actor actor = new Actor();
	private Genre genre = new Genre();
	private CinemaSeats cinemaSeats = new CinemaSeats();

	private List<Projection> projectionList;
	private List<Theater> theaterList;
	private List<Cinema> cinemaList;
	private List<Movie> movieList;
	private List<Actor> actorList;
	private List<Genre> genreList;
	private List<CinemaSeats> cinemaSeatsList;

	@PostConstruct
	public void init() {
		projectionList = projectionService.getProjections();
		theaterList = theaterService.getTheaters();
		cinemaList = cinemaService.getCinemas();
		movieList = movieService.getMovies();
		actorList = actorService.getActors();
		setGenreList(genreService.getGenres());
		cinemaSeatsList = cinemaSeatsService.getCinemaSeats();
	}

	public void reloadAll() {
		projectionList = projectionService.getProjections();
		theaterList = theaterService.getTheaters();
		cinemaList = cinemaService.getCinemas();
		movieList = movieService.getMovies();
		actorList = actorService.getActors();
		setGenreList(genreService.getGenres());
		cinemaSeatsList = cinemaSeatsService.getCinemaSeats();
	}

	public void reloadProjection() {
		projectionList = projectionService.getProjections();
	}

	public void reloadTheater() {
		theaterList = theaterService.getTheaters();
	}

	public void reloadCinema() {
		cinemaList = cinemaService.getCinemas();
	}

	public void reloadMovie() {
		movieList = movieService.getMovies();
	}

	public void reloadActor() {
		actorList = actorService.getActors();
	}

	public void reloadGenre() {
		setGenreList(genreService.getGenres());
	}

	public void reloadCinemaSeats() {
		cinemaSeatsList = cinemaSeatsService.getCinemaSeats();
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

	public IActorService getActorService() {
		return actorService;
	}

	public void setActorService(IActorService actorService) {
		this.actorService = actorService;
	}

	public List<Actor> getActorList() {
		return actorList;
	}

	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}

	public ICinemaSeatsService getCinemaSeatsService() {
		return cinemaSeatsService;
	}

	public void setCinemaSeatsService(ICinemaSeatsService cinemaSeatsService) {
		this.cinemaSeatsService = cinemaSeatsService;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public CinemaSeats getCinemaSeats() {
		return cinemaSeats;
	}

	public void setCinemaSeats(CinemaSeats cinemaSeats) {
		this.cinemaSeats = cinemaSeats;
	}

	public List<CinemaSeats> getCinemaSeatsList() {
		return cinemaSeatsList;
	}

	public void setCinemaSeatsList(List<CinemaSeats> cinemaSeatsList) {
		this.cinemaSeatsList = cinemaSeatsList;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	public IGenreService getGenreService() {
		return genreService;
	}

	public void setGenreService(IGenreService genreService) {
		this.genreService = genreService;
	}

}
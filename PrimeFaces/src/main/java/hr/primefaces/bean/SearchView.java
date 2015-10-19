package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.model.Theater;
import hr.primefaces.model.User;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.ITheaterService;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ManagedBean(name = "searchMB")
@ApplicationScoped
public class SearchView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	IMovieService movieService;

	@ManagedProperty(value = "#{TheaterService}")
	ITheaterService theaterService;

	@ManagedProperty(value = "#{UserService}")
	IUserService userService;

	public String searchInput;

	public Movie movie;
	public Actor actor;
	public Theater theater;
	public Genre genre;
	public User user;

	private List<Movie> selectedMovieList;

	public List<Movie> completeMovie(String input) {
		List<Movie> list = movieService.getMovieByName(input);
		return list;
	}

	public List<Actor> completeActor(String input) {
		List<Actor> list = (List<Actor>) movieService.getActorByName(input);
		return list;
	}

	public List<Theater> completeTheater(String input) {
		List<Theater> list = (List<Theater>) theaterService.getTheaterByName(input);
		return list;
	}

	public List<Genre> completeGenre(String input) {
		List<Genre> list = (List<Genre>) movieService.getGenreByName(input);
		return list;
	}

	public List<User> completeUser(String input) {
		List<User> list = (List<User>) userService.getUserByUsername(input);
		return list;
	}

	public void handleSelect() {
		System.out.print("test");
	}

	public void onItemSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
	}

	public IMovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(IMovieService movieService) {
		this.movieService = movieService;
	}

	public String getSearchInput() {
		return searchInput;
	}

	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public ITheaterService getTheaterService() {
		return theaterService;
	}

	public void setTheaterService(ITheaterService theaterService) {
		this.theaterService = theaterService;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public List<Movie> getSelectedMovieList() {
		return selectedMovieList;
	}

	public void setSelectedMovieList(List<Movie> selectedMovieList) {
		this.selectedMovieList = selectedMovieList;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
package hr.primefaces.bean;

import hr.primefaces.model.Actor;
import hr.primefaces.model.Genre;
import hr.primefaces.model.Movie;
import hr.primefaces.model.User;
import hr.primefaces.service.IMovieService;
import hr.primefaces.service.IUserService;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "autocompleteMB")
@ApplicationScoped
public class AutocompleteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{MovieService}")
	private IMovieService movieService;

	@ManagedProperty(value = "#{UserService}")
	private IUserService userService;

	/**
	 * completeMovie
	 * @param p_input
	 * @return
	 */
	public List<Movie> completeMovie(final String p_input) {
		return getMovieService().getMovieByName(p_input);
	}

	/**
	 * completeActor
	 * @param p_input
	 * @return
	 */
	public List<Actor> completeActor(final String p_input) {
		return getMovieService().getActorByName(p_input);
	}

	/**
	 * completeGenre
	 * @param p_input
	 * @return
	 */
	public List<Genre> completeGenre(final String p_input) {
		return getMovieService().getGenreByName(p_input);
	}

	/**
	 * completeUser
	 * @param p_input
	 * @return
	 */
	public List<User> completeUser(final String p_input) {
		return getUserService().getUserByUsername(p_input);
	}

	/**
	 * ################# GETTERS AND SETTERS #################
	 */

	/**
	 * @return the movieService
	 */
	public IMovieService getMovieService() {
		return movieService;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

	/**
	 * @param p_userService the userService to set
	 */
	public void setUserService(final IUserService p_userService) {
		this.userService = p_userService;
	}

}
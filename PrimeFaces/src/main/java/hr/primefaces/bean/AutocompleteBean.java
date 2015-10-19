package hr.primefaces.bean;

import hr.primefaces.model.Movie;
import hr.primefaces.service.IMovieService;

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

	/**
	 * completeMovie
	 * -funkcija za dohvat
	 * liste filmova
	 */
	public List<Movie> completeMovie(final String p_input) {
		return getMovieService().getMovieByName(p_input);
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
	 * @param p_movieService the movieService to set
	 */
	public void setMovieService(final IMovieService p_movieService) {
		this.movieService = p_movieService;
	}

}